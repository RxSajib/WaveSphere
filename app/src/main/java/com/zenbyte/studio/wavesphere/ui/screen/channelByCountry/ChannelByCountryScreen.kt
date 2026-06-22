package com.zenbyte.studio.wavesphere.ui.screen.channelByCountry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import coil3.compose.LocalPlatformContext
import com.zenbyte.studio.presentation.viewmodel.getChannelByCountry.GetChannelByCountryViewModel
import com.zenbyte.studio.wavesphere.root.LocalPlayerService
import com.zenbyte.studio.wavesphere.ui.component.MyCustomAppBar
import com.zenbyte.studio.wavesphere.ui.component.MyCustomStation
import com.zenbyte.studio.wavesphere.ui.navigation.AppDestination

@Composable
fun ChannelByCountryScreen(backStack: NavBackStack<NavKey>) {

    val contextCoil = LocalPlatformContext.current
    val viewModel : GetChannelByCountryViewModel = hiltViewModel()
    val channelListState by viewModel.channelList.collectAsStateWithLifecycle()
    val items = remember(channelListState) { channelListState }
    val playerService = LocalPlayerService.current
    val serviceChannel by playerService?.currentChannelFlow?.collectAsStateWithLifecycle() ?: remember { mutableStateOf(null) }


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
                        isSelected = serviceChannel?.stationuuid == items[index].stationuuid,
                        context = contextCoil, myChannel = items[index],
                        onClick = {myChannel ->
                            backStack.add(
                                AppDestination.Dest.PlayerView(channel = myChannel, channelList = items)
                            )
                        }
                    )
                }
            }
        }
    }


}