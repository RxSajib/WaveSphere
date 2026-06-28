package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.ui.component.MyLottie
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.presentation.ui.theme.genresColor

@Composable
fun MusicPlayingIndicator(modifier: Modifier) {
    Box(modifier = modifier.size(20.dp).clip(shape = CircleShape).background(color = genresColor)){
        MyLottie(
            animationResId = R.raw.mywave,
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.BottomStart),
        )
    }
}

