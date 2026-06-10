package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.wavesphere.ui.theme.buttonColor
import com.zenbyte.studio.wavesphere.ui.theme.playButtonColor
import com.zenbyte.studio.wavesphere.ui.theme.playIconColor

@Composable
fun MyCustomPlayButton(modifier: Modifier = Modifier) {
    FloatingActionButton(onClick = {}, shape = CircleShape, containerColor = playButtonColor, elevation = FloatingActionButtonDefaults.elevation(1.dp)) {
        Icon(
            painter = painterResource(R.drawable.system_solid_26_play_hover_play),
            contentDescription = null,
            tint = playIconColor
        )
    }
}

@Composable
@Preview
fun MyCustomPlayButtonPreview(modifier: Modifier = Modifier) {
    MyCustomPlayButton()
}