package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import coil3.compose.LocalPlatformContext
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.component.ChannelItem
import com.zenbyte.studio.presentation.ui.component.CountryShimmer
import com.zenbyte.studio.presentation.ui.component.MyCustomAppBar
import com.zenbyte.studio.presentation.ui.component.ServerError
import com.zenbyte.studio.presentation.viewmodel.news.NewsChannelViewModel
import kotlinx.coroutines.flow.update

@Composable
fun AllNewsScreen(rootBackStack: NavBackStack<NavKey>) {

    val viewModel: NewsChannelViewModel = hiltViewModel()
    val newsList by viewModel.newsList.collectAsStateWithLifecycle()
    val context = LocalPlatformContext.current


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Scaffold(
            topBar = {
                MyCustomAppBar(title = stringResource(R.string.news)) {
                    rootBackStack.removeLastOrNull()
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
                if (newsList.isSuccess) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        newsList.data?.let { myChannels ->
                            items(myChannels) { channel ->
                                ChannelItem(
                                    modifier = Modifier.padding(horizontal = 10.dp),
                                    context = context,
                                    isBuffering = viewModel.isBufferingChannel(myChannel = channel).collectAsStateWithLifecycle(false).value,
                                    isPlaying = viewModel.isPlaying(myChannel = channel).collectAsStateWithLifecycle(false).value,
                                    myChannel = channel,
                                    isChannelFavorite =
                                        viewModel.getSaveChannel(channelID = channel.stationuuid).collectAsStateWithLifecycle(false).value,
                                    onClickFavorite = {myChannel ->
                                        viewModel.saveChannel(myChannel = myChannel)
                                    },
                                    onMediaController = {myChannel ->
                                        viewModel.mediaPlayController(
                                            myChannel = myChannel,
                                            channels =  newsList.data?: emptyList(),
                                            index =  newsList.data?.indexOf(channel)?: -1
                                        )
                                    }
                                )
                            }
                        }

                    }
                } else if (newsList.isLoading) {
                    CountryShimmer()
                } else {
                    // error message
                    ServerError {
                        //  viewModel.getNewsList(countryCode = "in")
                    }
                }
            }
        }
    }
}