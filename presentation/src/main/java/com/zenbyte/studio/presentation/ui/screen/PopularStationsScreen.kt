package com.zenbyte.studio.presentation.ui.screen

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import coil3.compose.LocalPlatformContext
import com.zenbyte.studio.presentation.ui.component.MyCustomAppBar
import com.zenbyte.studio.presentation.ui.component.MyCustomStation
import com.zenbyte.studio.presentation.ui.component.MyCustomStationShimmerItem
import com.zenbyte.studio.presentation.ui.component.ServerError
import com.zenbyte.studio.presentation.ui.navigation.AppDestination
import com.zenbyte.studio.presentation.viewmodel.popularStations.PopularStationsViewModel
import com.zenbyte.studio.presentation.viewmodel.state.ApiState

@Composable
fun PopularStationsScreen(rootBackStack: NavBackStack<NavKey>) {

    val viewModel : PopularStationsViewModel = hiltViewModel()
    val channelListState by viewModel.sortedChannels.collectAsStateWithLifecycle(ApiState())
    val contextCoil = LocalPlatformContext.current

    Surface(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {

        Scaffold(
            topBar = {
                MyCustomAppBar(title = stringResource(com.zenbyte.studio.presentation.R.string.popular_stations)) {
                    rootBackStack.removeLastOrNull()
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding()), contentAlignment = Alignment.Center){

                if(channelListState.isSuccess){
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(channelListState.data?: emptyList()) { channelData ->
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
                else if(channelListState.isLoading){
                    LazyVerticalGrid(modifier = Modifier.fillMaxSize(), columns = GridCells.Fixed(3),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(16.dp)) {
                        items(30){
                            MyCustomStationShimmerItem()
                        }
                    }
                }else {
                    ServerError{

                    }
                }

            }
        }
    }
}