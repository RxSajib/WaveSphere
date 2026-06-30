package com.zenbyte.studio.wavesphere.app

import android.app.Application
import androidx.media3.exoplayer.ExoPlayer
import com.zenbyte.studio.wavesphere.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        private var _exoPlayer: ExoPlayer? = null
        val exoPlayer: ExoPlayer
            get() {
                if (_exoPlayer == null) {
                    throw IllegalStateException("ExoPlayer not initialized. Call initExoPlayer() first.")
                }
                return _exoPlayer!!
            }

        @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
        fun initExoPlayer(application: Application) {
            if (_exoPlayer == null) {
                val httpDataSourceFactory = androidx.media3.datasource.DefaultHttpDataSource.Factory()
                    .setUserAgent("WaveSphere/1.0 (Android)")
                    .setAllowCrossProtocolRedirects(true)
                
                val dataSourceFactory = androidx.media3.datasource.DefaultDataSource.Factory(application, httpDataSourceFactory)
                
                _exoPlayer = ExoPlayer.Builder(application)
                    .setMediaSourceFactory(androidx.media3.exoplayer.source.DefaultMediaSourceFactory(application).setDataSourceFactory(dataSourceFactory))
                    .build()
            }
        }

        fun releaseExoPlayer() {
            _exoPlayer?.release()
            _exoPlayer = null
        }
    }

    override fun onCreate() {
        super.onCreate()
        initExoPlayer(this)
    }
}