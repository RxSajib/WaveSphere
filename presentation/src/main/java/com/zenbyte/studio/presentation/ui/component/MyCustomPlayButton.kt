package com.zenbyte.studio.presentation.ui.component

import android.graphics.Color
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.playButtonColor
import com.zenbyte.studio.presentation.ui.theme.playIconColor

@Composable
fun MyCustomPlayButton(isPlaying : Boolean = false, isBuffering : Boolean = false, onClick: () -> Unit) {
    FloatingActionButton(onClick = {
        onClick.invoke()
    }, shape = CircleShape, containerColor = playButtonColor, elevation = FloatingActionButtonDefaults.elevation(1.dp)) {
        if(isBuffering){
            CircularProgressIndicator(
                modifier = Modifier.size(15.dp),
                color = androidx.compose.ui.graphics.Color.White,
                strokeWidth = 2.dp
            )
        }else {
            Icon(
                painter =if(isPlaying) painterResource(R.drawable.pause) else painterResource(R.drawable.system_solid_26_play_hover_play),
                contentDescription = null,
                tint = playIconColor
            )
        }

    }
}

