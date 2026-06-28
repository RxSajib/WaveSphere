package com.zenbyte.studio.wavesphere.ui.screen.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.zenbyte.studio.presentation.ui.component.HeightGap
import com.zenbyte.studio.presentation.viewmodel.player.PlayerViewModel
import com.zenbyte.studio.wavesphere.ui.component.GeneralSetting
import com.zenbyte.studio.wavesphere.ui.component.OtherSetting
import com.zenbyte.studio.wavesphere.ui.component.PremiumStatusCard
import com.zenbyte.studio.wavesphere.ui.navigation.AppDestination

@Composable
fun PlayerScreen(rootBackStack: NavBackStack<NavKey>) {

    val viewModel : PlayerViewModel = hiltViewModel()
    val darkModeToggle by viewModel.darkModeToggle.collectAsStateWithLifecycle()
    val dataSaverToggle by viewModel.dataSaverToggle.collectAsStateWithLifecycle()

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(state = rememberScrollState())) {
        HeightGap(height = 10.dp)
        PremiumStatusCard(modifier = Modifier.padding(horizontal = 16.dp))
        HeightGap(height = 15.dp)
        GeneralSetting(
            modifier = Modifier.padding(horizontal = 16.dp),
            onClickEqualizer = {},
            onClickSleepTime = {},
            isDarkModeCheck = darkModeToggle,
            onClickPlayBackSetting = {},
            isDataSaverCheck = dataSaverToggle,
            isDataSaverChanged = {checked ->
                viewModel.onDataSaverToggle(checked)
            },
            onDarkModeChanged = {checked ->
                viewModel.onDarkModeToggle(checked)
            },
            onClickPremium = {
                rootBackStack.add(
                    AppDestination.Dest(
                        AppDestination.Dest.Premium::class.simpleName ?: ""
                    )
                )
            }
        )
        HeightGap(height = 15.dp)
        OtherSetting(
            modifier = Modifier.padding(horizontal = 16.dp), onClickAbout = {
                rootBackStack.add(
                    AppDestination.Dest(
                        firstDestName = AppDestination.Dest.AboutUs::class.simpleName ?: ""
                    )
                )
            },
            onClickHelpAndSupport = {},
            onClickLanguage = {},
            onClickPremium = {
                rootBackStack.add(
                    AppDestination.Dest(
                        AppDestination.Dest.Premium::class.simpleName ?: ""
                    )
                )
            },
            onClickRecording = {}
        )
        HeightGap(height = 10.dp)
    }
}