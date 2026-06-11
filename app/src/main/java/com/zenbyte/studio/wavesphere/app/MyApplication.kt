package com.zenbyte.studio.wavesphere.app

import android.app.Application
import androidx.media3.exoplayer.ExoPlayer
import com.zenbyte.studio.wavesphere.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object{
        lateinit var exoPlayer: ExoPlayer
    }


    override fun onCreate() {

        exoPlayer = ExoPlayer.Builder(this).build()
        super.onCreate()
    }
}