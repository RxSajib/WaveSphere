package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.LocalPlatformContext
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.presentation.ui.component.ChannelItem
import com.zenbyte.studio.presentation.ui.component.CountryShimmer
import com.zenbyte.studio.presentation.ui.component.ServerError
import com.zenbyte.studio.presentation.viewmodel.search.SearchViewModel
import com.zenbyte.studio.presentation.viewmodel.state.ApiState

@Composable
fun NewsChannelListScreen(newsList: State<ApiState<List<MyChannel>>>, viewModel: SearchViewModel) {
    val context = LocalPlatformContext.current

    if (newsList.value.isSuccess) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            newsList.value.data?.let { myChannels ->
                items(myChannels) { channel ->
                    ChannelItem(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        context = context,
                        myChannel = channel,
                        isChannelFavorite = false,
                        onClickFavorite = {},
                        onMediaController = {})
                }
            }

        }
    } else if (newsList.value.isLoading) {
        CountryShimmer()
    } else {
        // error message
        ServerError {
            viewModel.getNewsList(countryCode = "in")
        }
    }

}