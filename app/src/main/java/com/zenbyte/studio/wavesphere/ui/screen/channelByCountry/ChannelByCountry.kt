package com.zenbyte.studio.wavesphere.ui.screen.channelByCountry

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.zenbyte.studio.presentation.viewmodel.getChannelByCountry.GetChannelByCountryViewModel

@Composable
fun ChannelByCountry(modifier: Modifier = Modifier) {

    val viewModel : GetChannelByCountryViewModel = hiltViewModel()

}