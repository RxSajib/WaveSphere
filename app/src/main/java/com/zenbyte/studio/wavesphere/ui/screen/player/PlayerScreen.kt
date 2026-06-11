package com.zenbyte.studio.wavesphere.ui.screen.player

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PlayerScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Player", style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
    }
}