package com.zenbyte.studio.wavesphere.service


import android.content.Intent
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaLibraryService
import androidx.media3.session.MediaSession
import com.zenbyte.studio.wavesphere.utils.MyCustomLogger
import com.zenbyte.studio.wavesphere.WaveSphereApp
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "PlayerService"

@AndroidEntryPoint
class PlayerService (
) : MediaLibraryService() {


    private lateinit var mediaSession: MediaSession

    @OptIn(UnstableApi::class)
    override fun onCreate() {
        super.onCreate()
        MyCustomLogger.logMessageInfo(tag = TAG, message = "on create call")
    }


    override fun onDestroy() {
        mediaSession.release()
        WaveSphereApp.releaseExoPlayer()
        super.onDestroy()
    }

    private val callback = object : MediaLibrarySession.Callback {

        @OptIn(UnstableApi::class)
        override fun onConnect(
            session: MediaSession,
            controller: MediaSession.ControllerInfo
        ): MediaSession.ConnectionResult {

            return MediaSession.ConnectionResult.AcceptedResultBuilder(session)
                .build()
        }
    }


    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaLibrarySession {
        val session = MediaLibrarySession.Builder(this, WaveSphereApp.exoPlayer, callback).build()
        return session
    }


    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        if (!WaveSphereApp.exoPlayer.isPlaying) {
            stopSelf()
        }
    }


}
