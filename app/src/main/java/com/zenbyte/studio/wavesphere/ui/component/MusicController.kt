package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.wavesphere.ui.theme.genresColor

@Composable
fun MusicController(icon : Painter, isPlayPushButton : Boolean = false, onClick: () -> Unit) {
    Box(modifier = Modifier.size(80.dp).clip(shape = CircleShape).background(color = if(isPlayPushButton) genresColor else Color.Transparent).clip(shape = CircleShape).clickable{
        onClick.invoke()
    }, contentAlignment = Alignment.Center){
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(30.dp),
        )
    }
}