package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HeightSpace(height : Dp ) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun WidthSpace(width : Dp) {
    Spacer(modifier = Modifier.height(width))
}