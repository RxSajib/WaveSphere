package com.zenbyte.studio.wavesphere.ui.screen.channelByCountry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.presentation.viewmodel.getChannelByCountry.GetChannelByCountryViewModel
import com.zenbyte.studio.wavesphere.MediaPlayerViewModel
import com.zenbyte.studio.wavesphere.ui.component.ChannelItem
import com.zenbyte.studio.wavesphere.ui.component.MyCustomAppBar
import com.zenbyte.studio.wavesphere.ui.component.MyCustomStation

@Composable
fun ChannelByCountryScreen(modifier: Modifier = Modifier) {

    val contextCoil = LocalPlatformContext.current
    val viewModel : GetChannelByCountryViewModel = hiltViewModel()
    val channelListState by viewModel.channelList.collectAsStateWithLifecycle()
    val mediaPlayerViewModel : MediaPlayerViewModel = hiltViewModel()
    val items = remember(channelListState) { channelListState }

    Scaffold(
        topBar = {
            MyCustomAppBar(
                title = "Bangladesh"
            ) {

            }
        }
    ) {innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)){
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(items.size) { index ->
                    MyCustomStation(
                        context = contextCoil, myChannel = items[index],
                        onClick = {myChannel ->
                            mediaPlayerViewModel.playMusic(myChannel = myChannel)
                        }
                    )
                }
            }
        }
    }


}