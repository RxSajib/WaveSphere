package com.zenbyte.studio.wavesphere.ui.screen.channelByCountry

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import com.zenbyte.studio.presentation.viewmodel.getChannelByCountry.GetChannelByCountryViewModel
import com.zenbyte.studio.wavesphere.ui.component.ChannelItem

@Composable
fun ChannelByCountry(modifier: Modifier = Modifier) {

    val contextCoil = LocalPlatformContext.current
    val viewModel : GetChannelByCountryViewModel = hiltViewModel()
    val channelList = viewModel.channelList.collectAsStateWithLifecycle()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(channelList.value){myChannel ->
            ChannelItem(
                myChannel = myChannel,
                context = contextCoil
            )
        }
    }
}