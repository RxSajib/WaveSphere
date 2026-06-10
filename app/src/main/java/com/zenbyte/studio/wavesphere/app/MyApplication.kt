package com.zenbyte.studio.wavesphere.app

import android.app.Application
import com.zenbyte.studio.wavesphere.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {


        super.onCreate()
    }
}