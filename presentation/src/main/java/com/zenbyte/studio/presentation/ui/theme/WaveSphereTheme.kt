package com.zenbyte.studio.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zenbyte.studio.presentation.viewmodel.utils.RootViewModel

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun WaveSphereTheme(appContent: @Composable () -> Unit){

    val viewModel : RootViewModel = hiltViewModel()
    val isDarkModeEnable = viewModel.darkModeToggle.collectAsStateWithLifecycle()

    val appThemes = isSystemInDarkTheme()
    val appColors = if (appThemes) darkColorScheme else lightColorScheme

    MaterialTheme (
        motionScheme = MotionScheme.expressive(),
        colorScheme = if(isDarkModeEnable.value) darkColorScheme else  lightColorScheme,
        content = appContent,
        typography = getMaterialTypography()
    )
}