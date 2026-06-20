package com.zenbyte.studio.wavesphere.ui.screen.channelByCountry

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import com.zenbyte.studio.presentation.viewmodel.getChannelByCountry.GetChannelByCountryViewModel
import com.zenbyte.studio.wavesphere.ui.component.ChannelItem

@Composable
fun ChannelByCountryScreen(modifier: Modifier = Modifier) {

    val contextCoil = LocalPlatformContext.current
    val viewModel : GetChannelByCountryViewModel = hiltViewModel()
    val channelListState by viewModel.channelList.collectAsStateWithLifecycle()

    val items = remember(channelListState) { channelListState }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        itemsIndexed(
            items = items,
            key = { _, item -> item.stationuuid },
            contentType = { _, _ -> "channel" }
        ) { index, myChannel ->
            ChannelItem(
                myChannel = myChannel,
                context = contextCoil,
                modifier = Modifier
            )
            if (index < items.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                )
            }
        }
    }
}