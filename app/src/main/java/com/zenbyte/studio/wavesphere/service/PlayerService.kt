package com.zenbyte.studio.wavesphere.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.content.pm.ServiceInfo
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.annotation.OptIn
import androidx.core.app.NotificationCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.common.ForwardingPlayer
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.CommandButton
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import androidx.media3.session.MediaStyleNotificationHelper
import androidx.media3.session.SessionCommand
import androidx.media3.session.SessionResult
import androidx.palette.graphics.Palette
import coil3.ImageLoader
import coil3.asDrawable
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.presentation.viewmodel.utils.MyCustomLogger
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.wavesphere.app.MyApplication
import com.zenbyte.studio.wavesphere.root.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "PlayerService"


class PlayerService : MediaSessionService() {

    private lateinit var exoPlayer: ExoPlayer
    private var mediaSession: MediaSession? = null
    private var currentChannel: MyChannel? = null
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private var _channelList = listOf<MyChannel>()
    private var currentIndex = -1

    private val _currentChannelFlow = MutableStateFlow<MyChannel?>(null)
    val currentChannelFlow = _currentChannelFlow.asStateFlow()

    inner class PlayerBinder : Binder() {
        fun getService(): PlayerService = this@PlayerService
    }

    private val binder = PlayerBinder()

    companion object {
        const val CHANNEL_ID = "player_channel"
        const val NOTIFICATION_ID = 1
        const val ACTION_PLAY = "com.zenbyte.studio.wavesphere.ACTION_PLAY"
        const val ACTION_PAUSE = "com.zenbyte.studio.wavesphere.ACTION_PAUSE"
        const val ACTION_STOP = "com.zenbyte.studio.wavesphere.ACTION_STOP"
        const val ACTION_NEXT = "com.zenbyte.studio.wavesphere.ACTION_NEXT"
        const val ACTION_PREVIOUS = "com.zenbyte.studio.wavesphere.ACTION_PREVIOUS"
        const val ACTION_FAVORITE = "com.zenbyte.studio.wavesphere.ACTION_FAVORITE"
    }

    private val playerListener = object : Player.Listener {
        override fun onEvents(player: Player, events: Player.Events) {
            if (events.containsAny(
                    Player.EVENT_PLAY_WHEN_READY_CHANGED,
                    Player.EVENT_PLAYBACK_STATE_CHANGED,
                    Player.EVENT_IS_PLAYING_CHANGED,
                    Player.EVENT_MEDIA_METADATA_CHANGED
                )
            ) {
                currentChannel?.let { updateNotification(it) }
            }
        }
    }

    @OptIn(UnstableApi::class)
    override fun onCreate() {
        super.onCreate()
        exoPlayer = MyApplication.exoPlayer
        exoPlayer.addListener(playerListener)

        // Force commands to be available even for live streams
        val forwardingPlayer = object : ForwardingPlayer(exoPlayer) {
            override fun getAvailableCommands(): Player.Commands {
                return super.getAvailableCommands().buildUpon()
                    .add(COMMAND_PLAY_PAUSE)
                    .add(COMMAND_STOP)
                    .add(COMMAND_SEEK_TO_NEXT)
                    .add(COMMAND_SEEK_TO_PREVIOUS)
                    .add(COMMAND_SEEK_TO_NEXT_MEDIA_ITEM)
                    .add(COMMAND_SEEK_TO_PREVIOUS_MEDIA_ITEM)
                    .build()
            }

            override fun isCommandAvailable(command: Int): Boolean {
                return when (command) {
                    COMMAND_PLAY_PAUSE, COMMAND_STOP,
                    COMMAND_SEEK_TO_NEXT, COMMAND_SEEK_TO_PREVIOUS,
                    COMMAND_SEEK_TO_NEXT_MEDIA_ITEM, COMMAND_SEEK_TO_PREVIOUS_MEDIA_ITEM -> true
                    else -> super.isCommandAvailable(command)
                }
            }

            override fun seekToNext() {
                MyCustomLogger.logMessageInfo(TAG, "System SeekToNext triggered")
                playNext()
            }

            override fun seekToPrevious() {
                MyCustomLogger.logMessageInfo(TAG, "System SeekToPrevious triggered")
                playPrevious()
            }

            override fun seekToNextMediaItem() {
                seekToNext()
            }

            override fun seekToPreviousMediaItem() {
                seekToPrevious()
            }
        }

        val favoriteCommand = SessionCommand(ACTION_FAVORITE, Bundle.EMPTY)
        val favoriteButton = CommandButton.Builder()
            .setSessionCommand(favoriteCommand)
            .setIconResId(android.R.drawable.btn_star_big_on)
            .setDisplayName("Favorite")
            .build()

        val nextCommand = SessionCommand(ACTION_NEXT, Bundle.EMPTY)
        val nextButton = CommandButton.Builder()
            .setSessionCommand(nextCommand)
            .setIconResId(android.R.drawable.ic_media_next)
            .setDisplayName("Next")
            .build()

        val previousCommand = SessionCommand(ACTION_PREVIOUS, Bundle.EMPTY)
        val previousButton = CommandButton.Builder()
            .setSessionCommand(previousCommand)
            .setIconResId(android.R.drawable.ic_media_previous)
            .setDisplayName("Previous")
            .build()

        mediaSession = MediaSession.Builder(this, forwardingPlayer)
            .setCallback(object : MediaSession.Callback {
                @OptIn(UnstableApi::class)
                override fun onConnect(
                    session: MediaSession,
                    controller: MediaSession.ControllerInfo
                ): MediaSession.ConnectionResult {
                    val playerCommands = MediaSession.ConnectionResult.DEFAULT_PLAYER_COMMANDS.buildUpon()
                        .add(Player.COMMAND_PLAY_PAUSE)
                        .add(Player.COMMAND_STOP)
                        .add(Player.COMMAND_SEEK_TO_NEXT)
                        .add(Player.COMMAND_SEEK_TO_PREVIOUS)
                        .add(Player.COMMAND_SEEK_TO_NEXT_MEDIA_ITEM)
                        .add(Player.COMMAND_SEEK_TO_PREVIOUS_MEDIA_ITEM)
                        .build()
                    val sessionCommands = MediaSession.ConnectionResult.DEFAULT_SESSION_COMMANDS.buildUpon()
                        .add(favoriteCommand)
                        .add(nextCommand)
                        .add(previousCommand)
                        .build()
                    return MediaSession.ConnectionResult.AcceptedResultBuilder(session)
                        .setAvailablePlayerCommands(playerCommands)
                        .setAvailableSessionCommands(sessionCommands)
                        .setCustomLayout(listOf(previousButton, favoriteButton, nextButton))
                        .build()
                }

                override fun onCustomCommand(
                    session: MediaSession,
                    controller: MediaSession.ControllerInfo,
                    customCommand: SessionCommand,
                    args: Bundle
                ): ListenableFuture<SessionResult> {
                    when (customCommand.customAction) {
                        ACTION_FAVORITE -> {
                            MyCustomLogger.logMessageInfo(TAG, "Custom Command Favorite triggered")
                            // Handle favorite logic here
                        }
                        ACTION_NEXT -> {
                            MyCustomLogger.logMessageInfo(TAG, "Custom Command Next triggered")
                            playNext()
                        }
                        ACTION_PREVIOUS -> {
                            MyCustomLogger.logMessageInfo(TAG, "Custom Command Previous triggered")
                            playPrevious()
                        }
                    }
                    return Futures.immediateFuture(SessionResult(SessionResult.RESULT_SUCCESS))
                }
            })
            .build()
        createNotificationChannel()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? = mediaSession

    override fun onBind(intent: Intent?): IBinder? {
        val action = intent?.action
        return if (action == SERVICE_INTERFACE) {
            super.onBind(intent)
        } else {
            binder
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_PLAY -> exoPlayer.play()
            ACTION_PAUSE -> exoPlayer.pause()
            ACTION_STOP -> {
                stopService()
                return START_NOT_STICKY
            }
            ACTION_NEXT -> {
                MyCustomLogger.logMessageInfo(TAG, "ACTION_NEXT triggered")
                playNext()
            }
            ACTION_PREVIOUS -> {
                MyCustomLogger.logMessageInfo(TAG, "ACTION_PREVIOUS triggered")
                playPrevious()
            }
            ACTION_FAVORITE -> {
                MyCustomLogger.logMessageInfo(TAG, "ACTION_FAVORITE triggered")
                // Handle favorite logic here
            }
        }

        startForegroundService()

        if (currentChannel != null) {
            updateNotification(currentChannel!!)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun stopService() {
        exoPlayer.stop()
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    fun setChannelList(list: List<MyChannel>) {
        _channelList = list
    }

    fun playNext() {
        if (_channelList.isEmpty()) return
        currentIndex = if (currentIndex < _channelList.size - 1) currentIndex + 1 else 0
        playChannel(_channelList[currentIndex])
    }

    fun playPrevious() {
        if (_channelList.isEmpty()) return
        currentIndex = if (currentIndex > 0) currentIndex - 1 else _channelList.size - 1
        playChannel(_channelList[currentIndex])
    }

    fun playChannel(channel: MyChannel) {
        currentChannel = channel
        _currentChannelFlow.value = channel
        currentIndex = _channelList.indexOfFirst { it.name == channel.name }
        try {
            val streamUrl = channel.urlResolved.ifEmpty { channel.url }
            MyCustomLogger.logMessageInfo(TAG, "Playing channel: ${channel.name} with URL: $streamUrl")

            val mediaMetadata = MediaMetadata.Builder()
                .setTitle(channel.name)
                .setArtist(channel.tags.ifEmpty { "Live Radio" })
                .build()

            val mediaItem = MediaItem.Builder()
                .setUri(streamUrl)
                .setMediaMetadata(mediaMetadata)
                .build()

            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.play()

            updateNotification(channel)
        } catch (e: Exception) {
            MyCustomLogger.logMessageInfo(TAG, "Error playing channel: ${e.message}")
        }
    }

    private fun startForegroundService() {
        val notification = currentChannel?.let { 
            createMediaNotification(it, null) 
        } ?: createSimpleNotification("Ready to play")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(
                NOTIFICATION_ID,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
            )
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
    }

    @OptIn(UnstableApi::class)
    private fun updateNotification(channel: MyChannel) {
        serviceScope.launch {
            val albumArt = loadBitmap(channel.favicon)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.notify(NOTIFICATION_ID, createMediaNotification(channel, albumArt))
        }
    }

    private suspend fun loadBitmap(url: String): Bitmap? {
        return try {
            val loader = ImageLoader(this)
            val request = ImageRequest.Builder(this)
                .data(url)
                .build()
            val result = loader.execute(request)
            if (result is SuccessResult) {
                val bitmap = (result.image.asDrawable(resources) as? BitmapDrawable)?.bitmap
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && bitmap?.config == Bitmap.Config.HARDWARE) {
                    bitmap.copy(Bitmap.Config.ARGB_8888, false)
                } else {
                    bitmap
                }
            } else null
        } catch (e: Exception) {
            null
        }
    }

    private fun createSimpleNotification(content: String): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("WaveSphere")
            .setContentText(content)
            .setSmallIcon(R.drawable.applogowhite)
            .setOngoing(true)
            .build()
    }


    @OptIn(UnstableApi::class)
    private fun createMediaNotification(channel: MyChannel, albumArt: Bitmap?): Notification {
        val activityIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, activityIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val previousIntent = Intent(this, PlayerService::class.java).apply { action = ACTION_PREVIOUS }
        val previousPendingIntent = PendingIntent.getService(this, 5, previousIntent, PendingIntent.FLAG_IMMUTABLE)

        val pauseIntent = Intent(this, PlayerService::class.java).apply { action = ACTION_PAUSE }
        val pausePendingIntent = PendingIntent.getService(this, 1, pauseIntent, PendingIntent.FLAG_IMMUTABLE)

        val playIntent = Intent(this, PlayerService::class.java).apply { action = ACTION_PLAY }
        val playPendingIntent = PendingIntent.getService(this, 2, playIntent, PendingIntent.FLAG_IMMUTABLE)

        val nextIntent = Intent(this, PlayerService::class.java).apply { action = ACTION_NEXT }
        val nextPendingIntent = PendingIntent.getService(this, 4, nextIntent, PendingIntent.FLAG_IMMUTABLE)

        val stopIntent = Intent(this, PlayerService::class.java).apply { action = ACTION_STOP }
        val stopPendingIntent = PendingIntent.getService(this, 3, stopIntent, PendingIntent.FLAG_IMMUTABLE)

        val favoriteIntent = Intent(this, PlayerService::class.java).apply { action = ACTION_FAVORITE }
        val favoritePendingIntent = PendingIntent.getService(this, 6, favoriteIntent, PendingIntent.FLAG_IMMUTABLE)

        val backgroundColor = albumArt?.let { Palette.from(it).generate().getDominantColor(0xFF1DB954.toInt()) }
            ?: 0xFF1DB954.toInt()

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(channel.name)
            .setContentText(channel.tags.ifEmpty { "Live Radio" })
            .setSmallIcon(R.drawable.applogowhite)
            .setLargeIcon(albumArt)
            .setContentIntent(pendingIntent)
            .setOngoing(exoPlayer.isPlaying)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setColor(backgroundColor)
            .setColorized(true)
            .addAction(android.R.drawable.ic_media_previous, "Previous", previousPendingIntent) // Index 0

        if (exoPlayer.isPlaying) {
            builder.addAction(android.R.drawable.ic_media_pause, "Pause", pausePendingIntent) // Index 1
        } else {
            builder.addAction(android.R.drawable.ic_media_play, "Play", playPendingIntent) // Index 1
        }

        builder.addAction(android.R.drawable.ic_media_next, "Next", nextPendingIntent) // Index 2
            .addAction(android.R.drawable.ic_menu_close_clear_cancel, "Stop", stopPendingIntent) // Index 4
            .setStyle(
                MediaStyleNotificationHelper.MediaStyle(mediaSession!!)
            )

        return builder.build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Player Service Channel",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Controls for media playback"
                setShowBadge(false)
            }
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        if (!exoPlayer.isPlaying) {
            stopSelf()
        }
    }

    override fun onDestroy() {
        serviceScope.cancel()
        exoPlayer.removeListener(playerListener)
        mediaSession?.release()
        mediaSession = null
        super.onDestroy()
        exoPlayer.stop()
    }
}
