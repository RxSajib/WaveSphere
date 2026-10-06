package com.zenbyte.studio.presentation.ui.player

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory

object MyPLayer {

    var _exoPlayer: ExoPlayer? = null
    val exoPlayer: ExoPlayer
        get() {
            if (_exoPlayer == null) {
                throw IllegalStateException("ExoPlayer not initialized. Call initExoPlayer() first.")
            }
            return _exoPlayer!!
        }

    @OptIn(UnstableApi::class)
    fun initExoPlayer(application: Context) {
        if (_exoPlayer == null) {
            Log.d("EXOPLAYER", "initExoPlayer: ")
            val httpDataSourceFactory = DefaultHttpDataSource.Factory()
                .setUserAgent("WaveSphere/1.0 (Android)")
                .setAllowCrossProtocolRedirects(true)

            val dataSourceFactory =
                DefaultDataSource.Factory(application, httpDataSourceFactory)

            _exoPlayer = ExoPlayer.Builder(application)
                .setMediaSourceFactory(
                    DefaultMediaSourceFactory(application).setDataSourceFactory(
                        dataSourceFactory
                    )
                )
                .build()
        }
    }

    fun releaseExoPlayer() {
        _exoPlayer?.release()
        _exoPlayer = null
    }
}