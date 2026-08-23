package com.zenbyte.studio.presentation.ui.navigation

import androidx.navigation3.runtime.NavKey
import com.zenbyte.studio.data.local.model.Genres
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.model.MyCountry
import com.zenbyte.studio.domain.model.MyGenres
import kotlinx.serialization.Serializable

@Serializable
sealed class AppDestination : NavKey {

    @Serializable
    data object BottomAppBar : AppDestination() {

        @Serializable
        data object Home : AppDestination()

        @Serializable
        data object Search : AppDestination()


        @Serializable
        data object Favorite : AppDestination()

        @Serializable
        data object Player : AppDestination()
    }



    @Serializable
    data class Dest(val firstDestName : String,
                    val countryName: String? = null,
        val genres: MyGenres?= null,
        val languages: com.zenbyte.studio.data.local.model.Languages?= null) : AppDestination()

    {

        @Serializable
        data object Languages : AppDestination()

        @Serializable
        data object TrendingStations : AppDestination()

        @Serializable
        data object PopularStations : AppDestination()


        @Serializable
        data object AllCountry : AppDestination()

        @Serializable
        data object MyCountryList : AppDestination()

        @Serializable
        data object Genres : AppDestination()

        @Serializable
        data object News : AppDestination()

        @Serializable
        data class ChannelByCountry(val name: String) : AppDestination()

        @Serializable
        data class PlayerView(val channel: MyChannel, val channelList : List<MyChannel> = emptyList()) : AppDestination()

        @Serializable
        data object AboutUs : AppDestination()

        @Serializable
        data object Premium : AppDestination()

        @Serializable
        data class ChannelByGenres(val genres: MyGenres) : AppDestination()

        @Serializable
        data class ChannelByLanguages(val languages: com.zenbyte.studio.data.local.model.Languages) : AppDestination()
    }
}