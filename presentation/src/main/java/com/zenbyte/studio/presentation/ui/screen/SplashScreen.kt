package com.zenbyte.studio.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
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
import com.zenbyte.studio.presentation.ui.navigation.AppDestination
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.ui.theme.buttonColor
import com.zenbyte.studio.presentation.ui.theme.lottieCircleColor
import com.zenbyte.studio.presentation.viewmodel.splashScreen.SplashScreenViewModel

@Composable
fun SplashScreen(rootBackStack: NavBackStack<NavKey>) {

    val viewModel : SplashScreenViewModel = hiltViewModel()
    val isNavigateHome by viewModel.navigateToHome.collectAsStateWithLifecycle(false)

    if(isNavigateHome){
        rootBackStack.add(
            AppDestination.BottomAppBar
        )
        rootBackStack.remove(AppDestination.SplashScreen)
    }

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding()),
            contentAlignment = Alignment.Center
        ) {

            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.loading)
            )

            val compositionTwo by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.world)
            )
            val waveColor = rememberLottieDynamicProperties(
                LottieDynamicProperty(
                    property = LottieProperty.STROKE_COLOR,
                    value = lottieCircleColor.toArgb(),
                    keyPath = KeyPath("**", "Stroke 1")
                )
            )

            LottieAnimation(
                dynamicProperties = waveColor,
                composition = composition,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                iterations = LottieConstants.IterateForever,
                speed = 0.1f
            )


            val colorForWorld = rememberLottieDynamicProperties(
                LottieDynamicProperty(
                    property = LottieProperty.COLOR,
                    value = Color.White.toArgb(),
                    keyPath = KeyPath("**")
                )
            )

            LottieAnimation(
                dynamicProperties = colorForWorld,
                composition = compositionTwo,
                modifier = Modifier.fillMaxSize(),
                iterations = LottieConstants.IterateForever,
                speed = 1f
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
                            fontWeight = FontWeight.W900,
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