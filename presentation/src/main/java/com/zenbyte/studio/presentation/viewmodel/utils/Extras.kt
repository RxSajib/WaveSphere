package com.zenbyte.studio.presentation.viewmodel.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.TelephonyManager
import androidx.compose.ui.graphics.Color
import com.zenbyte.studio.presentation.BuildConfig
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.Blues
import com.zenbyte.studio.presentation.ui.theme.Classical
import com.zenbyte.studio.presentation.ui.theme.Country
import com.zenbyte.studio.presentation.ui.theme.Dance
import com.zenbyte.studio.presentation.ui.theme.Electronic
import com.zenbyte.studio.presentation.ui.theme.Folk
import com.zenbyte.studio.presentation.ui.theme.HipHop
import com.zenbyte.studio.presentation.ui.theme.Jazz
import com.zenbyte.studio.presentation.ui.theme.Latin
import com.zenbyte.studio.presentation.ui.theme.Metal
import com.zenbyte.studio.presentation.ui.theme.Pop
import com.zenbyte.studio.presentation.ui.theme.Reggae
import com.zenbyte.studio.presentation.ui.theme.RnB
import com.zenbyte.studio.presentation.ui.theme.Rock
import com.zenbyte.studio.presentation.ui.theme.World
import com.zenbyte.studio.presentation.viewmodel.utils.enum.ChannelLanguages
import com.zenbyte.studio.presentation.viewmodel.utils.enum.GenresEnum

object Extras {

    fun Context.getAppVersion() : String {
        return try {
            val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager.getPackageInfo(
                    packageName,
                    PackageManager.PackageInfoFlags.of(0)
                )
            } else {
                @Suppress("DEPRECATION")
                packageManager.getPackageInfo(packageName, 0)
            }
            packageInfo.versionName ?: "1.0.0"
        } catch (e: Exception) {
            "1.0.0"
        }
    }

    fun Context.getSimCountry(): String {
        val telephonyManager =
            getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephonyManager.simCountryIso
    }

    fun Context.getGenresName(genre: GenresEnum): String {
        return when (genre) {
            GenresEnum.Pop -> getString(R.string.genre_pop)
            GenresEnum.Rock -> getString(R.string.genre_rock)
            GenresEnum.HipHop -> getString(R.string.genre_hip_hop)
            GenresEnum.Jazz -> getString(R.string.genre_jazz)
            GenresEnum.Classical -> getString(R.string.genre_classical)
            GenresEnum.Electronic -> getString(R.string.genre_electronic)
            GenresEnum.Dance -> getString(R.string.genre_dance)
            GenresEnum.Country -> getString(R.string.genre_country)
            GenresEnum.RB -> getString(R.string.genre_rb)
            GenresEnum.Reggane -> getString(R.string.genre_reggae)
            GenresEnum.Latin -> getString(R.string.genre_latin)
            GenresEnum.Metal -> getString(R.string.genre_metal)
            GenresEnum.Blues -> getString(R.string.genre_blues)
            GenresEnum.Folk -> getString(R.string.genre_folk)
            GenresEnum.World -> getString(R.string.genre_world)
        }
    }

    fun getLanguagesName(languages: ChannelLanguages, context: Context): String {
        return when (languages) {
            ChannelLanguages.English -> context.getString(R.string.language_english)
            ChannelLanguages.Chinese -> context.getString(R.string.language_chinese)
            ChannelLanguages.Hindi -> context.getString(R.string.language_hindi)
            ChannelLanguages.Spanish -> context.getString(R.string.language_spanish)
            ChannelLanguages.French -> context.getString(R.string.language_french)
            ChannelLanguages.Arabic -> context.getString(R.string.language_arabic)
            ChannelLanguages.Bengali -> context.getString(R.string.language_bengali)
            ChannelLanguages.Portuguese -> context.getString(R.string.language_portuguese)
            ChannelLanguages.Russian -> context.getString(R.string.language_russian)
            ChannelLanguages.Japanese -> context.getString(R.string.language_japanese)
            ChannelLanguages.German -> context.getString(R.string.language_german)
            ChannelLanguages.Korean -> context.getString(R.string.language_korean)
            ChannelLanguages.Italian -> context.getString(R.string.language_italian)
            ChannelLanguages.Turkish -> context.getString(R.string.language_turkish)
            ChannelLanguages.Vietnamese -> context.getString(R.string.language_vietnamese)
            ChannelLanguages.Indonesian -> context.getString(R.string.language_indonesian)
            ChannelLanguages.Urdu -> context.getString(R.string.language_urdu)
            ChannelLanguages.Persian -> context.getString(R.string.language_persian)
            ChannelLanguages.Thai -> context.getString(R.string.language_thai)
            ChannelLanguages.Dutch -> context.getString(R.string.language_dutch)
        }
    }

    fun getLanguagesColor(languages: ChannelLanguages) : Color{
        return when(languages){
            ChannelLanguages.English -> HipHop
            ChannelLanguages.Chinese -> Reggae
            ChannelLanguages.Hindi -> World
            ChannelLanguages.Spanish -> Folk
            ChannelLanguages.French -> Blues
            ChannelLanguages.Arabic -> Metal
            ChannelLanguages.Bengali -> Latin
            ChannelLanguages.Portuguese -> Reggae
            ChannelLanguages.Russian -> RnB
            ChannelLanguages.Japanese -> Country
            ChannelLanguages.German -> Dance
            ChannelLanguages.Korean -> Electronic
            ChannelLanguages.Italian -> Classical
            ChannelLanguages.Turkish -> Jazz
            ChannelLanguages.Vietnamese -> HipHop
            ChannelLanguages.Indonesian -> Rock
            ChannelLanguages.Urdu -> Pop
            ChannelLanguages.Persian -> HipHop
            ChannelLanguages.Thai -> Country
            ChannelLanguages.Dutch -> Electronic
        }
    }

    fun getGenreIcon(genre: GenresEnum) : Int{
       return when(genre){
            GenresEnum.Pop -> R.drawable.icon_star
            GenresEnum.Rock -> R.drawable.icon_guitar
            GenresEnum.HipHop -> R.drawable.icon_microphone
            GenresEnum.Jazz -> R.drawable.icon_wave
            GenresEnum.Classical -> R.drawable.icon_piano
            GenresEnum.Electronic -> R.drawable.icon_headphones
            GenresEnum.Dance -> R.drawable.icon_album
            GenresEnum.Country -> R.drawable.world_svgrepo_com
            GenresEnum.RB -> R.drawable.icon_favorite_heart_hover_pinch
            GenresEnum.Reggane -> R.drawable.icon_nature
            GenresEnum.Latin -> R.drawable.icon_musicnote
            GenresEnum.Metal -> R.drawable.icon_bolt
            GenresEnum.Blues -> R.drawable.icon_piano
            GenresEnum.Folk -> R.drawable.icon_guitar
            GenresEnum.World -> R.drawable.world_svgrepo_com
        }
    }

      fun getGenreBackgroundColor(genre: GenresEnum) : Color{
       return when(genre){
            GenresEnum.Pop -> Pop
            GenresEnum.Rock -> Rock
            GenresEnum.HipHop -> HipHop
            GenresEnum.Jazz -> Jazz
            GenresEnum.Classical -> Classical
            GenresEnum.Electronic -> Electronic
            GenresEnum.Dance -> Dance
            GenresEnum.Country -> Country
            GenresEnum.RB -> RnB
            GenresEnum.Reggane -> Reggae
            GenresEnum.Latin -> Latin
            GenresEnum.Metal -> Metal
            GenresEnum.Blues -> Blues
            GenresEnum.Folk -> Folk
            GenresEnum.World -> World
        }
    }




}