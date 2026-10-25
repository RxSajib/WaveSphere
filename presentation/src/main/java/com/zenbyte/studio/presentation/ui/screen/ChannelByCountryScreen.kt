package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.component.CategoryList
import com.zenbyte.studio.presentation.ui.component.ChannelItem
import com.zenbyte.studio.presentation.ui.component.HeightGap
import com.zenbyte.studio.presentation.ui.component.HomeHeader
import com.zenbyte.studio.presentation.ui.component.MyCustomAppBar
import com.zenbyte.studio.presentation.ui.component.MyCustomStation
import com.zenbyte.studio.presentation.ui.component.MyCustomStationShimmerItem
import com.zenbyte.studio.presentation.ui.component.MySectionHeader
import com.zenbyte.studio.presentation.ui.component.NowPlayingComponent
import com.zenbyte.studio.presentation.ui.component.ServerError
import com.zenbyte.studio.presentation.viewmodel.getChannelByCountry.GetChannelByCountryViewModel
import com.zenbyte.studio.presentation.ui.navigation.AppDestination
import com.zenbyte.studio.presentation.viewmodel.utils.MyCustomLogger

private const val TAG = "ChannelByCountryScreen"
@Composable
fun ChannelByCountryScreen(
    backStack: NavBackStack<NavKey>,
    countryName: String
) {

    val contextCoil = LocalPlatformContext.current
    val viewModel : GetChannelByCountryViewModel = hiltViewModel()
    val channelListState by viewModel.channelList.collectAsStateWithLifecycle()
    val currentPlayingChannel by viewModel.currentPlayingChannel.collectAsStateWithLifecycle()
    val channelState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getChannelByCountryRemote(countryName = countryName)
        viewModel.getChannelByCountry(countryName = countryName)
    }

    Scaffold(
        topBar = {
            MyCustomAppBar(
                title = countryName,
                isBackButtonEnable = true,
                isPremiumEnable = false
            ) {
                backStack.removeLastOrNull()
            }
        }
    ) {innerPadding ->


        Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
            when{
                channelState.isInitialLoading -> {
                    LazyVerticalGrid(modifier = Modifier.fillMaxSize(), columns = GridCells.Fixed(3),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(16.dp)) {
                        items(30){
                            MyCustomStationShimmerItem()
                        }
                    }
                }
                channelState.data.isNotEmpty() -> {
                    MyCustomLogger.logMessageInfo(tag = TAG, message = "data ---")

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(channelState.data as List<MyChannel>) { channelData ->
                            MyCustomStation(
                                isPlaying = viewModel.isPlaying(myChannel = channelData).collectAsStateWithLifecycle(false).value,
                                context = contextCoil, myChannel = channelData,
                                onClick = {myChannel ->
                                    backStack.add(
                                        AppDestination.Dest.PlayerView(channel = myChannel, channelList = channelListState.data?: emptyList())
                                    )
                                }
                            )
                        }
                    }
                }
                channelState.error != null -> {
                    MyCustomLogger.logMessageInfo(tag = TAG, message = "error ---")
                    ServerError{
                        viewModel.getChannelByCountryRemote(countryName = countryName)
                    }
                }
            }

        }


        /*  Box(modifier = Modifier
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
                                  backStack.add(
                                      AppDestination.Dest.PlayerView(channel = myChannel, channelList = channelListState.data?: emptyList())
                                  )
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
                      viewModel.getChannelByCountry(countryName = countryName)
                  }
              }


          }*/
    }


}