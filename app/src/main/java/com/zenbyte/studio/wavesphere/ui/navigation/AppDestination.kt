package com.zenbyte.studio.wavesphere.ui.navigation

import androidx.navigation3.runtime.NavKey
import com.zenbyte.studio.domain.model.MyChannel
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
    data class Dest(val firstDestName : String) : AppDestination() {

        @Serializable
        data object ChannelByCountry : AppDestination()

        @Serializable
        data class PlayerView(val channel: MyChannel, val channelList : List<MyChannel> = emptyList()) : AppDestination()
    }
}