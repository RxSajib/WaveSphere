package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.component.AudioQualityGroup
import com.zenbyte.studio.presentation.ui.component.MyCustomAppBar
import com.zenbyte.studio.presentation.viewmodel.playbackSetting.PlaybackSettingViewModel

@Composable
fun PlaybackSettingScreen() {
    val context = LocalContext.current
    val viewModel : PlaybackSettingViewModel = hiltViewModel()
    Surface(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface)) {
        Scaffold(
            topBar = {
                MyCustomAppBar(title = stringResource(R.string.playback_setting)) { }
            }
        ) { innerPadding ->
            Column(modifier = Modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding())) {
                AudioQualityGroup(context = context)
            }
        }
    }
}