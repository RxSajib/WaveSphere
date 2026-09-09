package com.zenbyte.studio.wavesphere

import android.app.Application
import android.content.res.Resources
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import com.zenbyte.studio.presentation.ui.player.MyPLayer.initExoPlayer
import dagger.hilt.android.HiltAndroidApp
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.app_locale.SharedPrefsAppLocaleRepository
import dev.b3nedikt.reword.RewordInterceptor
import dev.b3nedikt.viewpump.ViewPump
import java.util.Locale

@HiltAndroidApp
class WaveSphereApp : Application() {


    override fun onCreate() {
        super.onCreate()
        initExoPlayer(this)
        ViewPump.init(RewordInterceptor)

        AppLocale.supportedLocales = listOf(
            Locale.ENGLISH,
            Locale.forLanguageTag("bn"),
            Locale.forLanguageTag("hi"),
            Locale.forLanguageTag("iw"),
            Locale.forLanguageTag("ru"),
            Locale.forLanguageTag("zh"),
        )
        AppLocale.appLocaleRepository = SharedPrefsAppLocaleRepository(this)
    }

    override fun getResources(): Resources {
        return AppLocale.wrapResources(this, super.getResources())
    }
}