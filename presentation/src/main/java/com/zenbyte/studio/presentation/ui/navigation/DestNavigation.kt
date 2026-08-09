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
import com.zenbyte.studio.data.local.model.Genres
import com.zenbyte.studio.domain.model.MyGenres
import com.zenbyte.studio.presentation.ui.screen.AboutScreen
import com.zenbyte.studio.presentation.ui.screen.ChannelByCountryScreen
import com.zenbyte.studio.presentation.ui.screen.ChannelByGenresScreen
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
                subclass(AppDestination.Dest.ChannelByGenres::class, AppDestination.Dest.ChannelByGenres.serializer())
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
            }
        }
    }


    val firstDest = when {

        startDest.firstDestName == "ChannelByCountry" ->
            AppDestination.Dest.ChannelByCountry(startDest.countryName ?: "Unknown")

        startDest.firstDestName == "ChannelByGenres" -> AppDestination.Dest.ChannelByGenres(startDest.genres?: MyGenres())

        startDest.firstDestName == AppDestination.Dest.AboutUs::class.simpleName -> AppDestination.Dest.AboutUs
        startDest.firstDestName == AppDestination.Dest.Premium::class.simpleName -> AppDestination.Dest.Premium
        startDest.firstDestName == AppDestination.Dest.TrendingStations::class.simpleName -> AppDestination.Dest.TrendingStations
        startDest.firstDestName == AppDestination.Dest.PopularStations::class.simpleName -> AppDestination.Dest.PopularStations
        /*  startDest.firstDestName == AppDestination.Dest.Premium::class.simpleName -> AppDestination.Dest.Premium
         startDest.firstDestName == AppDestination.Dest.UploadStories::class.simpleName -> AppDestination.Dest.UploadStories
         startDest.firstDestName == AppDestination.Dest.SubscriptionHistory::class.simpleName -> AppDestination.Dest.SubscriptionHistory
         startDest.firstDestName == AppDestination.Dest.StoryTypeWiseBook::class.simpleName -> AppDestination.Dest.StoryTypeWiseBook(
             typeName = ""
         )

         startDest.firstDestName == AppDestination.Dest.SearchStoryResult::class.simpleName -> AppDestination.Dest.SearchStoryResult
         startDest.firstDestName == AppDestination.Dest.PublishedPendingStory::class.simpleName -> AppDestination.Dest.PublishedPendingStory
         startDest.firstDestName == AppDestination.Dest.AllReleaseStory::class.simpleName -> AppDestination.Dest.AllReleaseStory
         startDest.firstDestName == AppDestination.Dest.NewReleaseStory::class.simpleName -> AppDestination.Dest.NewReleaseStory
         startDest.firstDestName == AppDestination.Dest.MostPopular::class.simpleName -> AppDestination.Dest.MostPopular
         startDest.firstDestName == AppDestination.Dest.ChangeLanguage::class.simpleName -> AppDestination.Dest.ChangeLanguage
         startDest.firstDestName == AppDestination.Dest.StoryDetails::class.simpleName -> AppDestination.Dest.StoryDetails
         startDest.firstDestName == AppDestination.Dest.PublishedStory::class.simpleName -> AppDestination.Dest.PublishedStory
 */
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
                    ChannelByGenresScreen(rootBackStack = rootBackStack, genres = it.genres)
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