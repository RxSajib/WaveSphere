package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.LottieDynamicProperty
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.model.KeyPath
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.component.DownloadChannel
import com.zenbyte.studio.presentation.ui.component.HeightGap
import com.zenbyte.studio.presentation.ui.component.MyLottie
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.ui.theme.buttonColor
import com.zenbyte.studio.presentation.ui.theme.colorSurfaceTintDark
import com.zenbyte.studio.presentation.ui.theme.colorSurfaceTintLight
import com.zenbyte.studio.presentation.ui.theme.lottieCircleColor

@Composable
fun SplashScreen() {
    Scaffold() { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding()),
            contentAlignment = Alignment.Center
        ) {

            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.loading)
            )
            val dynamicProperties = rememberLottieDynamicProperties(
                LottieDynamicProperty(
                    property = LottieProperty.STROKE_COLOR,
                    value = lottieCircleColor.toArgb(),
                    keyPath = KeyPath("**", "Stroke 1")
                )
            )
            LottieAnimation(
                dynamicProperties = dynamicProperties,
                composition = composition,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                iterations = LottieConstants.IterateForever,
                speed = 0.1f
            )

            MyLottie(
                animationResId = R.raw.world,
                modifier = Modifier.fillMaxSize(),
                iterations = Int.MAX_VALUE
            )


            Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Image(
                        painter = painterResource(R.drawable.myapplogo),
                        contentDescription = null,
                        modifier = Modifier.size(150.dp),
                        colorFilter = ColorFilter.tint(color = buttonColor)
                    )

                    HeightGap(height = 25.dp)

                    Text(
                        text = "W A V E  S P H E R E",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    HeightGap(height = 10.dp)
                    Text(
                        text = "Media & Broadcasting Services",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.W300,
                            fontSize = adjustedFontSize(10f),
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                        )
                    )

                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    MyLottie(
                        animationResId = R.raw.recording,
                        modifier = Modifier.size(50.dp)
                    )
                    DownloadChannel(modifier = Modifier.padding(16.dp))
                }

            }

        }

    }
}

@Composable
@Preview
fun Prev() {
    SplashScreen()
}