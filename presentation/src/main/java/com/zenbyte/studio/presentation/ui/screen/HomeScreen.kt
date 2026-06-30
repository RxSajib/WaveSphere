package com.zenbyte.studio.presentation.ui.screen

import android.annotation.SuppressLint
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.LocalPlatformContext
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.component.HeightGap
import com.zenbyte.studio.presentation.viewmodel.home.HomeViewModel
import com.zenbyte.studio.presentation.ui.component.ChannelItem
import com.zenbyte.studio.presentation.ui.component.HomeHeader
import com.zenbyte.studio.presentation.ui.component.MyCustomStation
import com.zenbyte.studio.presentation.ui.component.MySectionHeader
import com.zenbyte.studio.presentation.ui.component.NowPlayingComponent


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val viewModel : HomeViewModel = hiltViewModel()

    val context = LocalPlatformContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val itemWidth = (screenWidth - 32.dp - 20.dp) / 4
    val trendingChannel = viewModel.tranChannel.collectAsStateWithLifecycle(emptyList())
    val popularShort = viewModel.popularStation.collectAsStateWithLifecycle(emptyList())



    LazyColumn (
        modifier = modifier
            .fillMaxSize().padding(16.dp)

    ) {

        item{
            HomeHeader()
            HeightGap(height = 20.dp)

            NowPlayingComponent(context)
            HeightGap(height = 15.dp)
            MySectionHeader(
                title = stringResource(R.string.trending_stations),
                showSeeAll = true,
                onClickSeeAll = {

                }
            )
            HeightGap(height = 20.dp)
        }


        item{
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(trendingChannel.value) { channel ->
                    Box(modifier = Modifier.width(itemWidth)) {
                        MyCustomStation(
                            context = context, myChannel = channel,
                            onClick = {myChannel ->
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
           /* CategoryList(
                onClickCountry = {},
                onClickLanguages = {},
                onClickGenres = {},
                onClickNews = {}
            )*/
            HeightGap(height = 20.dp)
            MySectionHeader(
                title = stringResource(R.string.popular_stations),
                showSeeAll = true,
                onClickSeeAll = {

                }
            )
        }

        items(popularShort.value) { channel ->
            ChannelItem(context = context, myChannel = channel, modifier = Modifier)
        }

    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}