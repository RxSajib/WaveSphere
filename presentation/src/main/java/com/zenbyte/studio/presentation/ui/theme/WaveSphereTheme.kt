package com.zenbyte.studio.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun WaveSphereTheme(appContent: @Composable () -> Unit){



    val appThemes = isSystemInDarkTheme()
    val appColors = if (appThemes) darkColorScheme else lightColorScheme

    MaterialTheme(
        colorScheme = appColors,
        content = appContent,
        typography = getMaterialTypography()
    )
}