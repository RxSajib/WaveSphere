package com.zenbyte.studio.wavesphere.ui.navigation

import androidx.navigation3.runtime.NavKey
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
}