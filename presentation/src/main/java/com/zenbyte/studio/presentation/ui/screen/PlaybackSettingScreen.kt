package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.component.AudioQualityGroup
import com.zenbyte.studio.presentation.ui.component.Crossfade
import com.zenbyte.studio.presentation.ui.component.HeightGap
import com.zenbyte.studio.presentation.ui.component.LiveStreamingGroup
import com.zenbyte.studio.presentation.ui.component.MyCustomAppBar
import com.zenbyte.studio.presentation.ui.component.WidthGap
import com.zenbyte.studio.presentation.ui.data.AppConstant.ENABLE_AUTO_RECONNECT
import com.zenbyte.studio.presentation.ui.data.AppConstant.ENABLE_CONTINUE_IN_BACKGROUND
import com.zenbyte.studio.presentation.ui.data.AppConstant.ENABLE_CROSS_FADE
import com.zenbyte.studio.presentation.ui.data.AppConstant.ENABLE_SHOW_LIVE_INDICATOR
import com.zenbyte.studio.presentation.ui.data.AppConstant.SEEKER_VALUE
import com.zenbyte.studio.presentation.viewmodel.playbackSetting.PlaybackSettingViewModel

@Composable
fun PlaybackSettingScreen() {
    val context = LocalContext.current
    val viewModel: PlaybackSettingViewModel = hiltViewModel()
    val isAutoReconnect by viewModel.isAutoReconnect.collectAsStateWithLifecycle(false)
    val isContinueInBackground by viewModel.isContinueInBackground.collectAsStateWithLifecycle(false)
    val isLiveIndicator by viewModel.isLiveIndicator.collectAsStateWithLifecycle(false)
    val isCrossFade by viewModel.isCrossFade.collectAsStateWithLifecycle(false)
    val seekerValue by viewModel.seekerValue.collectAsStateWithLifecycle(0f)
    val selectedAudioQuality by viewModel.selectedAudioQuality.collectAsStateWithLifecycle(null)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Scaffold(
            topBar = {
                MyCustomAppBar(title = stringResource(R.string.playback_setting)) { }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding())
                    .verticalScroll(state = rememberScrollState())
                    .padding(16.dp)

            ) {
                AudioQualityGroup(
                    context = context,
                    selectedQuality = selectedAudioQuality,
                    onQualitySelected = {
                        viewModel.saveAudioQuality(it)
                    }
                )
                HeightGap(height = 10.dp)
                Crossfade(
                    crossFadeValue = isCrossFade,
                    seekerValue = seekerValue?: 0.0f,
                    onSeekerValueChanged = {
                       viewModel.saveSeekerValue(
                            key = SEEKER_VALUE,
                            value = it
                        )
                    }
                ){
                    viewModel.saveSetting(key = ENABLE_CROSS_FADE, value = it)
                }
                HeightGap(height = 10.dp)
                LiveStreamingGroup(
                    autoReconnect = isAutoReconnect,
                    onReconnectChanged = {
                        viewModel.saveSetting(
                            key = ENABLE_AUTO_RECONNECT,
                            value = it
                        )
                    },
                    continueInBackground = isContinueInBackground,
                    onContinueBackgroundChanged = {
                        viewModel.saveSetting(
                            key = ENABLE_CONTINUE_IN_BACKGROUND,
                            value = it
                        )
                    },
                    showLiveIndicator = isLiveIndicator,
                    liveIndicatorChanged = {
                        viewModel.saveSetting(key = ENABLE_SHOW_LIVE_INDICATOR, value = it)
                    }
                )
            }
        }
    }
}