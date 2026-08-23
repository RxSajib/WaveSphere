package com.zenbyte.studio.presentation.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import com.zenbyte.studio.data.local.model.Languages
import com.zenbyte.studio.presentation.ui.component.MyCustomAppBar
import com.zenbyte.studio.presentation.ui.component.MyCustomStation
import com.zenbyte.studio.presentation.viewmodel.channelByLanguages.ChannelByLanguagesViewModel

@Composable
fun ChannelListByLanguagesScreen(languages: Languages) {

    val viewModel : ChannelByLanguagesViewModel = hiltViewModel()
    val channelsData = viewModel.channelList.collectAsStateWithLifecycle()
    val contextCoil = LocalPlatformContext.current

    LaunchedEffect(Unit) {
        viewModel.setLanguagesName(languagesName = languages.titleEnglish)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Scaffold(
            topBar = {
                MyCustomAppBar(
                    title = languages.title
                ) {
                    // back press
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)){
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(channelsData.value) { channelData ->
                        MyCustomStation(
                            isPlaying = viewModel.isPlaying(myChannel = channelData).collectAsStateWithLifecycle(false).value,
                            context = contextCoil, myChannel = channelData,
                            onClick = {myChannel ->
                                /* backStack.add(
                                     AppDestination.Dest.PlayerView(channel = myChannel, channelList = channelListState.data?: emptyList())
                                 )*/
                            }
                        )
                    }
                }
            }
        }
    }
}