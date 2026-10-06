package com.zenbyte.studio.presentation.ui.screen

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.zenbyte.studio.presentation.ui.bottomsheet.ChooseLanguagesSheet
import com.zenbyte.studio.presentation.ui.component.GeneralSetting
import com.zenbyte.studio.presentation.ui.component.HeightGap
import com.zenbyte.studio.presentation.ui.component.OtherSetting
import com.zenbyte.studio.presentation.ui.component.PremiumStatusCard
import com.zenbyte.studio.presentation.ui.data.AppConstant
import com.zenbyte.studio.presentation.viewmodel.player.PlayerViewModel
import com.zenbyte.studio.presentation.ui.navigation.AppDestination
import com.zenbyte.studio.presentation.viewmodel.utils.Extras.getAppVersion
import dev.b3nedikt.app_locale.AppLocale

@Composable
fun PlayerScreen(rootBackStack: NavBackStack<NavKey>) {

    val viewModel: PlayerViewModel = hiltViewModel()
    val context = LocalContext.current
    val activity = context as? Activity
    val darkModeToggle by viewModel.darkModeToggle.collectAsStateWithLifecycle()
    val dataSaverToggle by viewModel.dataSaverToggle.collectAsStateWithLifecycle()
    val languagesList by viewModel.appLanguages.collectAsStateWithLifecycle()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        HeightGap(height = 10.dp)
        PremiumStatusCard(modifier = Modifier.padding(horizontal = 16.dp))
        HeightGap(height = 15.dp)
        GeneralSetting(
            modifier = Modifier.padding(horizontal = 16.dp),
            onClickEqualizer = {},
            onClickSleepTime = {},
            isDarkModeCheck = darkModeToggle,
            onClickPlayBackSetting = {
                rootBackStack.add(
                    AppDestination.Dest(
                        AppDestination.Dest.PlaybackSetting::class.simpleName ?: ""
                    )
                )
            },
            isDataSaverCheck = dataSaverToggle,
            isDataSaverChanged = { checked ->
                viewModel.onDataSaverToggle(checked)
            },
            onDarkModeChanged = { checked ->
                viewModel.onDarkModeToggle(value = checked, key = AppConstant.ENABLE_DARK_MODE)
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
            selectedLanguages = languagesList.filter { it.code == AppLocale.currentLocale.language }[0].nativeName,
            modifier = Modifier.padding(horizontal = 16.dp), onClickAbout = {
                rootBackStack.add(
                    AppDestination.Dest(
                        firstDestName = AppDestination.Dest.AboutUs::class.simpleName ?: ""
                    )
                )
            },
            onClickHelpAndSupport = {
                rootBackStack.add(
                    AppDestination.Dest(AppDestination.Dest.HelpAndSupport::class.simpleName?: "")
                )
            },
            onClickLanguage = {
                viewModel.showLanguagesSheet = true
            },
            onClickPremium = {
                rootBackStack.add(
                    AppDestination.Dest(
                        AppDestination.Dest.Premium::class.simpleName ?: ""
                    )
                )
            },
            appVersionCode = context.getAppVersion(),
            onClickRecording = {}
        )
        HeightGap(height = 10.dp)
    }


    if (viewModel.showLanguagesSheet) {
        ChooseLanguagesSheet(viewModel = viewModel, selectedLanguages = {
            viewModel.showLanguagesSheet = false
            activity?.recreate()
        }, onDismissRequest = { viewModel.showLanguagesSheet = false })
    }
}