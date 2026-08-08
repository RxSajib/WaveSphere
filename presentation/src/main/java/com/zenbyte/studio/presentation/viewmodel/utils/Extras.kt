package com.zenbyte.studio.presentation.viewmodel.utils

import android.content.Context
import android.telephony.TelephonyManager
import androidx.compose.ui.graphics.Color
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
import com.zenbyte.studio.presentation.viewmodel.utils.enum.GenresEnum

object Extras {

    fun getSimCountry(context: Context): String {
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephonyManager.simCountryIso
    }

    fun getGenresName(genre: GenresEnum, context: Context): String {
        return when (genre) {
            GenresEnum.Pop -> context.getString(R.string.genre_pop)
            GenresEnum.Rock -> context.getString(R.string.genre_rock)
            GenresEnum.HipHop -> context.getString(R.string.genre_hip_hop)
            GenresEnum.Jazz -> context.getString(R.string.genre_jazz)
            GenresEnum.Classical -> context.getString(R.string.genre_classical)
            GenresEnum.Electronic -> context.getString(R.string.genre_electronic)
            GenresEnum.Dance -> context.getString(R.string.genre_dance)
            GenresEnum.Country -> context.getString(R.string.genre_country)
            GenresEnum.RB -> context.getString(R.string.genre_rb)
            GenresEnum.Reggane -> context.getString(R.string.genre_reggae)
            GenresEnum.Latin -> context.getString(R.string.genre_latin)
            GenresEnum.Metal -> context.getString(R.string.genre_metal)
            GenresEnum.Blues -> context.getString(R.string.genre_blues)
            GenresEnum.Folk -> context.getString(R.string.genre_folk)
            GenresEnum.World -> context.getString(R.string.genre_world)
        }
    }

    fun getGenreColor(genre: GenresEnum): String {
        return when (genre) {
            GenresEnum.Pop -> "#FF4081"
            GenresEnum.Rock -> "#F44336"
            GenresEnum.HipHop -> "#9C27B0"
            GenresEnum.Jazz -> "#673AB7"
            GenresEnum.Classical -> "#3F51B5"
            GenresEnum.Electronic -> "#2196F3"
            GenresEnum.Dance -> "#03A9F4"
            GenresEnum.Country -> "#00BCD4"
            GenresEnum.RB -> "#009688"
            GenresEnum.Reggane -> "#4CAF50"
            GenresEnum.Latin -> "#8BC34A"
            GenresEnum.Metal -> "#CDDC39"
            GenresEnum.Blues -> "#FFEB3B"
            GenresEnum.Folk -> "#FFC107"
            GenresEnum.World -> "#FF9800"
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