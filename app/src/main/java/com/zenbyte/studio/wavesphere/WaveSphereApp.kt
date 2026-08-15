package com.zenbyte.studio.wavesphere

import android.app.Application
import android.content.res.Resources
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import dagger.hilt.android.HiltAndroidApp
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.app_locale.SharedPrefsAppLocaleRepository
import dev.b3nedikt.reword.RewordInterceptor
import dev.b3nedikt.viewpump.ViewPump
import java.util.Locale

@HiltAndroidApp
class WaveSphereApp : Application() {

    companion object {
        private var _exoPlayer: ExoPlayer? = null
        val exoPlayer: ExoPlayer
            get() {
                if (_exoPlayer == null) {
                    throw IllegalStateException("ExoPlayer not initialized. Call initExoPlayer() first.")
                }
                return _exoPlayer!!
            }

        @OptIn(UnstableApi::class)
        fun initExoPlayer(application: Application) {
            if (_exoPlayer == null) {
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