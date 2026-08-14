package com.zenbyte.studio.presentation.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.LocalPlatformContext
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.component.HeightGap
import com.zenbyte.studio.presentation.viewmodel.home.HomeViewModel
import com.zenbyte.studio.presentation.ui.component.ChannelItem
import com.zenbyte.studio.presentation.ui.component.HomeHeader
import com.zenbyte.studio.presentation.ui.component.MyCustomStation
import com.zenbyte.studio.presentation.ui.component.MySectionHeader
import com.zenbyte.studio.presentation.ui.component.NowPlayingComponent
import com.zenbyte.studio.presentation.ui.navigation.AppDestination
import com.zenbyte.studio.presentation.ui.component.CategoryList
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "HomeScreen"

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(modifier: Modifier = Modifier, rootBackStack: NavBackStack<NavKey>) {

    val viewModel: HomeViewModel = hiltViewModel()

    val context = LocalPlatformContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val itemWidth = (screenWidth - 32.dp - 20.dp) / 4
    val trendingChannel = viewModel.tranChannel.collectAsStateWithLifecycle(emptyList())
    val popularShort = viewModel.popularStation.collectAsStateWithLifecycle(emptyList())
    val currentPlayingChannel by viewModel.currentPlayingChannel.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()


    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {

        item {
            HomeHeader()
            HeightGap(height = 20.dp)
            currentPlayingChannel?.let { channel ->
                NowPlayingComponent(context = context, channel = channel, viewModel = viewModel)
            }

            HeightGap(height = 15.dp)
            MySectionHeader(
                title = stringResource(R.string.trending_stations),
                showSeeAll = true,
                onClickSeeAll = {
                    rootBackStack.add(
                        AppDestination.Dest(
                            firstDestName = AppDestination.Dest.TrendingStations::class.simpleName
                                ?: ""
                        )
                    )
                }
            )
            HeightGap(height = 20.dp)
        }


        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(trendingChannel.value) { channel ->
                    Box(modifier = Modifier.width(itemWidth)) {
                        MyCustomStation(
                            context = context, myChannel = channel,
                            onClick = { myChannel ->
                                // mediaPlayerViewModel.playMusic(myChannel = myChannel)
                            }
                        )
                    }
                }
            }
        }

        item {
            HeightGap(height = 20.dp)
            MySectionHeader(
                title = stringResource(R.string.categories),
                showSeeAll = false
            )
            HeightGap(height = 15.dp)
            CategoryList(
                onClickCountry = {
                    rootBackStack.add(
                        AppDestination.Dest(firstDestName = AppDestination.Dest.MyCountryList::class.simpleName.orEmpty())
                    )
                },
                onClickLanguages = {
                    rootBackStack.add(
                        AppDestination.Dest(firstDestName = AppDestination.Dest.Languages::class.simpleName.orEmpty())
                    )
                },
                onClickGenres = {
                    rootBackStack.add(
                        AppDestination.Dest(firstDestName = AppDestination.Dest.Genres::class.simpleName.orEmpty())
                    )
                },
                onClickNews = {
                    rootBackStack.add(
                        AppDestination.Dest(firstDestName = AppDestination.Dest.News::class.simpleName.orEmpty())
                    )
                }
            )
            HeightGap(height = 20.dp)
            MySectionHeader(
                title = stringResource(R.string.popular_stations),
                showSeeAll = true,
                onClickSeeAll = {
                    rootBackStack.add(
                        AppDestination.Dest(
                            firstDestName = AppDestination.Dest.PopularStations::class.simpleName
                                ?: ""
                        )
                    )
                }
            )
        }

        items(popularShort.value) { channel ->
            ChannelItem(
                context = context,
                myChannel = channel,
                modifier = Modifier,
                isChannelFavorite =
                    viewModel.getSaveChannel(channelID = channel.stationuuid).collectAsStateWithLifecycle(false).value,
                onClickFavorite = { myChannel ->
                    viewModel.saveChannel(myChannel = myChannel)
                },
                onMediaController = { myChannel ->

                })
        }

    }

}

