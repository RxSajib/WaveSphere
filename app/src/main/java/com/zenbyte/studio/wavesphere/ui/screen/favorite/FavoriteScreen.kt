package com.zenbyte.studio.wavesphere.ui.screen.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import com.zenbyte.studio.presentation.viewmodel.favorite.FavoriteChannelViewModel
import com.zenbyte.studio.presentation.viewmodel.utils.MyCustomLogger
import com.zenbyte.studio.wavesphere.ui.component.ChannelItem
import com.zenbyte.studio.wavesphere.ui.component.EmptyChannel

private const val TAG = "FavoriteScreen"

@Composable
fun FavoriteScreen(modifier: Modifier = Modifier) {
    val contextCoil = LocalPlatformContext.current
    val viewModel: FavoriteChannelViewModel = hiltViewModel()
    val favoriteChannelList = viewModel.favoriteChannel.collectAsStateWithLifecycle(emptyList())

    MyCustomLogger.logMessageInfo(
        tag = TAG,
        message = "channel list ${favoriteChannelList.value.size}"
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (favoriteChannelList.value.isEmpty()) {
            EmptyChannel()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(favoriteChannelList.value) { myChannel ->
                    ChannelItem(context = contextCoil, myChannel = myChannel, modifier = Modifier)
                }
            }
        }

    }
}