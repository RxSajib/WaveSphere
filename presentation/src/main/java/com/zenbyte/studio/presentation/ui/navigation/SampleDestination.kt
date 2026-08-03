package com.zenbyte.studio.presentation.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.zenbyte.studio.presentation.ui.screen.ChannelByCountryScreen
import com.zenbyte.studio.presentation.ui.screen.CountriesScreen
import com.zenbyte.studio.presentation.ui.screen.PlayerViewScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun SampleDes() {
    val appConfig = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(AppDestination.Dest.ChannelByCountry::class, AppDestination.Dest.ChannelByCountry.serializer())
                subclass(AppDestination.Dest.PlayerView::class, AppDestination.Dest.PlayerView.serializer())
                subclass(AppDestination.Dest.AllCountry::class, AppDestination.Dest.AllCountry.serializer())
            }
        }
    }

    val rootBackStack = rememberNavBackStack(configuration = appConfig, AppDestination.Dest.AllCountry)

    Box(modifier = Modifier.fillMaxSize()) {

        NavDisplay(
            backStack = rootBackStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<AppDestination.Dest.AllCountry> {country ->
                //    CountriesScreen(rootBackStack = rootBackStack, viewModel = view)
                }
                entry<AppDestination.Dest.ChannelByCountry> {countryName ->
               //     ChannelByCountryScreen(backStack = rootBackStack, countryName)
                }
                entry<AppDestination.Dest.PlayerView> {
                    PlayerViewScreen( rootBackStack = rootBackStack, channelData = it)
                }

            },

            transitionSpec = {
                slideInHorizontally(initialOffsetX = { it }) togetherWith
                        slideOutHorizontally(targetOffsetX = { -it })
            },
            popTransitionSpec = {
                slideInHorizontally(initialOffsetX = { -it }) togetherWith
                        slideOutHorizontally(targetOffsetX = { it })
            },
            predictivePopTransitionSpec = {
                slideInHorizontally(initialOffsetX = { -it }) togetherWith
                        slideOutHorizontally(targetOffsetX = { it })
            },

            )


    }
}