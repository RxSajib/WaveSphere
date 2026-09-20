package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.component.HeightGap
import com.zenbyte.studio.presentation.ui.component.MyLottie
import com.zenbyte.studio.presentation.ui.theme.colorSurfaceTintDark
import com.zenbyte.studio.presentation.ui.theme.colorSurfaceTintLight

@Composable
fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        MyLottie(
           animationResId = R.raw.world,
            modifier = Modifier.fillMaxSize(),
            iterations = Int.MAX_VALUE
        )

        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            HeightGap(height = 10.dp)
            Text(
                text = "MEDIA & BROADCASTING SERVICES",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.W300,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                )
            )
            HeightGap(height = 20.dp)
            MyLottie(
                animationResId = R.raw.mywave
            )

        }

    }
}

@Composable
@Preview
fun Prev(){
    SplashScreen()
}