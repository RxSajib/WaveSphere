package com.zenbyte.studio.presentation.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.zenbyte.studio.domain.model.MyGenres
import com.zenbyte.studio.presentation.ui.screen.AboutScreen
import com.zenbyte.studio.presentation.ui.screen.AllCountryScreen
import com.zenbyte.studio.presentation.ui.screen.AllGenresScreen
import com.zenbyte.studio.presentation.ui.screen.AllNewsScreen
import com.zenbyte.studio.presentation.ui.screen.ChannelByCountryScreen
import com.zenbyte.studio.presentation.ui.screen.ChannelByGenresScreen
import com.zenbyte.studio.presentation.ui.screen.LanguagesListScreen
import com.zenbyte.studio.presentation.ui.screen.PlayerViewScreen
import com.zenbyte.studio.presentation.ui.screen.PopularStationsScreen
import com.zenbyte.studio.presentation.ui.screen.PremiumScreen
import com.zenbyte.studio.presentation.ui.screen.TrendingStationsScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun DestNavigation(
    startDest: AppDestination.Dest,
    rootBackStack: NavBackStack<NavKey>,
) {


    val appConfig = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(
                    AppDestination.Dest.ChannelByCountry::class,
                    AppDestination.Dest.ChannelByCountry.serializer()
                )
                subclass(
                    AppDestination.Dest.ChannelByGenres::class,
                    AppDestination.Dest.ChannelByGenres.serializer()
                )
                subclass(
                    AppDestination.Dest.PlayerView::class,
                    AppDestination.Dest.PlayerView.serializer()
                )
                subclass(
                    AppDestination.Dest.AboutUs::class,
                    AppDestination.Dest.AboutUs.serializer()
                )
                subclass(
                    AppDestination.Dest.Premium::class,
                    AppDestination.Dest.Premium.serializer()
                )
                subclass(
                    AppDestination.Dest.TrendingStations::class,
                    AppDestination.Dest.TrendingStations.serializer()
                )
                subclass(
                    AppDestination.Dest.PopularStations::class,
                    AppDestination.Dest.PopularStations.serializer()
                )
                subclass(
                    AppDestination.Dest.Languages::class,
                    AppDestination.Dest.Languages.serializer()
                )
                subclass(
                    AppDestination.Dest.MyCountryList::class,
                    AppDestination.Dest.MyCountryList.serializer()
                )
                subclass(
                    AppDestination.Dest.Genres::class,
                    AppDestination.Dest.Genres.serializer()
                )
                subclass(
                    AppDestination.Dest.News::class,
                    AppDestination.Dest.News.serializer()
                )
            }
        }
    }


    val firstDest = when {

        startDest.firstDestName == AppDestination.Dest.ChannelByCountry::class.simpleName ->
            AppDestination.Dest.ChannelByCountry(startDest.countryName ?: "Unknown")

        startDest.firstDestName == AppDestination.Dest.ChannelByGenres::class.simpleName -> AppDestination.Dest.ChannelByGenres(
            startDest.genres ?: MyGenres()
        )

        startDest.firstDestName == AppDestination.Dest.AboutUs::class.simpleName -> AppDestination.Dest.AboutUs
        startDest.firstDestName == AppDestination.Dest.News::class.simpleName -> AppDestination.Dest.News
        startDest.firstDestName == AppDestination.Dest.Premium::class.simpleName -> AppDestination.Dest.Premium
        startDest.firstDestName == AppDestination.Dest.TrendingStations::class.simpleName -> AppDestination.Dest.TrendingStations
        startDest.firstDestName == AppDestination.Dest.PopularStations::class.simpleName -> AppDestination.Dest.PopularStations
        startDest.firstDestName == AppDestination.Dest.Languages::class.simpleName -> AppDestination.Dest.Languages
        startDest.firstDestName == AppDestination.Dest.MyCountryList::class.simpleName -> AppDestination.Dest.MyCountryList
        startDest.firstDestName == AppDestination.Dest.Genres::class.simpleName -> AppDestination.Dest.Genres
        else -> throw Exception("Invalid destination")
    }


    val backStack = rememberNavBackStack(appConfig, firstDest)

    Box(modifier = Modifier.fillMaxSize()) {
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {

                entry<AppDestination.Dest.ChannelByCountry> {
                    ChannelByCountryScreen(backStack, it.name)
                }
                entry<AppDestination.Dest.PlayerView> { channelData ->
                    PlayerViewScreen(channelData = channelData, rootBackStack = rootBackStack)
                }
                entry<AppDestination.Dest.AboutUs> {
                    AboutScreen(rootBackStack = rootBackStack)
                }
                entry<AppDestination.Dest.Premium> {
                    PremiumScreen()
                }
                entry<AppDestination.Dest.TrendingStations> {
                    TrendingStationsScreen(rootBackStack = rootBackStack)
                }
                entry<AppDestination.Dest.PopularStations> {
                    PopularStationsScreen(rootBackStack = rootBackStack)
                }
                entry<AppDestination.Dest.ChannelByGenres> {
                    ChannelByGenresScreen(
                        rootBackStack = rootBackStack,
                        backStack = backStack,
                        genres = it.genres
                    )
                }
                entry<AppDestination.Dest.Languages> {
                    LanguagesListScreen(rootBackStack = rootBackStack)
                }
                entry<AppDestination.Dest.MyCountryList> {
                    AllCountryScreen(rootBackStack = rootBackStack)
                }
                entry<AppDestination.Dest.Genres> {
                    AllGenresScreen(rootBackStack = rootBackStack)
                }
                entry<AppDestination.Dest.News> {
                    AllNewsScreen(rootBackStack = rootBackStack)
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