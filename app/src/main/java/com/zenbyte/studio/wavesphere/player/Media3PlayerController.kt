package com.zenbyte.studio.wavesphere.player

import android.content.ComponentName
import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.Timeline
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.MoreExecutors
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.repository.PlayerController
import com.zenbyte.studio.wavesphere.service.PlayerService
import com.zenbyte.studio.wavesphere.utils.MyCustomLogger
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.jvm.Throws

private const val TAG = "Media3PlayerController"
@Singleton
class Media3PlayerController @Inject constructor(
    @ApplicationContext private val context: Context
) : PlayerController {

    private val _currentChannel = MutableStateFlow<MyChannel?>(null)
    override val currentChannel: StateFlow<MyChannel?> = _currentChannel.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    override val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    override val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private lateinit var mediaController: MediaController
    private var channelList: List<MyChannel> = emptyList()




    override fun play(
        channels: List<MyChannel>,
        startIndex: Int
    ) {
        try {
            this.channelList = channels

            val mediaItems = channels.map { channel ->
                MyCustomLogger.logMessageInfo(tag = TAG, message = "Creating MediaItem for: ${channel.name} with URL: ${channel.url}")

                MediaItem.Builder()
                    .setMediaId(channel.stationuuid)
                    .setUri(Uri.parse(channel.url))
                    .setMediaMetadata(
                        MediaMetadata.Builder()
                            .setTitle(channel.name)
                            .setArtist(channel.country)
                            .setAlbumTitle(channel.tags)
                            .setArtworkUri(channel.favicon.takeIf { it.isNotBlank() }?.toUri())
                            .build()
                    )
                    .build()
            }

            if (::mediaController.isInitialized) {
                MyCustomLogger.logMessageInfo(tag = TAG, message = "play() for channels: size=${mediaItems.size}, startIndex=$startIndex")
                mediaController.setMediaItems(mediaItems, startIndex, 0L)
                mediaController.prepare()
                mediaController.play()

                _currentChannel.value = channels.getOrNull(startIndex)
            } else {
                MyCustomLogger.logMessageInfo(tag = TAG, message = "mediaController not initialized yet")
            }
        } catch (e: Exception) {
            MyCustomLogger.logMessageInfo(tag = TAG, message = "Error in play(): ${e.message}")
        }
    }

    override fun pause() {
        if (::mediaController.isInitialized) {
            MyCustomLogger.logMessageInfo(tag = TAG, message = "pause() called")
            mediaController.pause()
        }
    }

    override fun stop() {
        if (::mediaController.isInitialized) {
            MyCustomLogger.logMessageInfo(tag = TAG, message = "stop() called")
            mediaController.stop()
        }
    }

    override fun next() {
        if (::mediaController.isInitialized) {
            MyCustomLogger.logMessageInfo(tag = TAG, message = "next() called")
            mediaController.seekToNextMediaItem()
        }
    }

    init {
        val sessionToken = SessionToken(
            context,
            ComponentName(context, PlayerService::class.java)
        )

        val controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()

        controllerFuture.addListener({
            try {
                mediaController = controllerFuture.get()
                mediaController.addListener(object : Player.Listener {
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        MyCustomLogger.logMessageInfo(tag = TAG, message = "onIsPlayingChanged: $isPlaying")
                        _isPlaying.value = isPlaying
                    }

                    override fun onPlaybackStateChanged(playbackState: Int) {
                        MyCustomLogger.logMessageInfo(tag = TAG, message = "onPlaybackStateChanged: $playbackState")
                        _isLoading.value = playbackState == Player.STATE_BUFFERING
                    }

                    override fun onPlayerError(error: PlaybackException) {
                        MyCustomLogger.logMessageInfo(tag = TAG, message = "Player error: ${error.errorCodeName} - ${error.message}")
                        _isPlaying.value = false
                        _isLoading.value = false
                    }

                    override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                        MyCustomLogger.logMessageInfo(tag = TAG, message = "onMediaItemTransition: reason=$reason, mediaId=${mediaItem?.mediaId}")
                        mediaItem?.let { item ->
                            val channel = channelList.find { it.stationuuid == item.mediaId }
                                ?: MyChannel(
                                    stationuuid = item.mediaId,
                                    name = item.mediaMetadata.title?.toString() ?: "",
                                    country = item.mediaMetadata.artist?.toString() ?: "",
                                    tags = item.mediaMetadata.albumTitle?.toString() ?: "",
                                    favicon = item.mediaMetadata.artworkUri?.toString() ?: "",
                                    url = item.localConfiguration?.uri?.toString() ?: ""
                                )
                            MyCustomLogger.logMessageInfo(tag = TAG, message = "Synced channel: ${channel.name}")
                            _currentChannel.value = channel
                        }
                    }
                })
                _isPlaying.value = mediaController.isPlaying
                mediaController.currentMediaItem?.let { item ->
                    val channel = channelList.find { it.stationuuid == item.mediaId }
                        ?: MyChannel(
                            stationuuid = item.mediaId,
                            name = item.mediaMetadata.title?.toString() ?: "",
                            country = item.mediaMetadata.artist?.toString() ?: "",
                            tags = item.mediaMetadata.albumTitle?.toString() ?: "",
                            favicon = item.mediaMetadata.artworkUri?.toString() ?: ""
                        )
                    _currentChannel.value = channel
                }
                MyCustomLogger.logMessageInfo(tag = TAG, message = "MediaController initialized and state synced")

            } catch (e: Exception) {
                MyCustomLogger.logMessageInfo(tag = TAG, message = "Error initializing MediaController: ${e.message}")
            }
        }, MoreExecutors.directExecutor())
    }

    override fun previous() {
        if (::mediaController.isInitialized) {
            MyCustomLogger.logMessageInfo(tag = TAG, message = "previous() called")
            mediaController.seekToPreviousMediaItem()
        }
    }

}
