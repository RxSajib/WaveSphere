package com.zenbyte.studio.presentation.ui.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.sin


@Composable
fun PremiumAudioWaveform(
    amplitudes: List<Float>,
    modifier: Modifier = Modifier,
    isPlaying: Boolean = true,
    barWidth: Dp = 3.dp,
    barSpacing: Dp = 3.dp,
    minBarHeight: Dp = 4.dp,
    maxBarHeight: Dp = 48.dp,
    gradientColors: List<Color> = listOf(Color(0xFFD0BCFF), Color(0xFF6650a4))
) {
    val density = LocalDensity.current

    val barWidthPx = with(density) { barWidth.toPx() }
    val barSpacingPx = with(density) { barSpacing.toPx() }
    val minHeightPx = with(density) { minBarHeight.toPx() }
    val maxHeightPx = with(density) { maxBarHeight.toPx() }

    val infiniteTransition = rememberInfiniteTransition(
        label = "waveform_animation"
    )

    val animationProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 900,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "wave_animation"
    )

    Canvas(
        modifier = modifier
    ) {
        if (amplitudes.isEmpty()) return@Canvas

        val totalBarWidth = barWidthPx + barSpacingPx

        val maxVisibleBars =
            (size.width / totalBarWidth).toInt()

        val visibleAmplitudes =
            amplitudes.takeLast(maxVisibleBars)

        val centerY = size.height / 2f

        visibleAmplitudes.forEachIndexed { index, amplitude ->

            val normalizedAmplitude =
                amplitude.coerceIn(0f, 1f)

            val waveOffset =
                if (isPlaying) {
                    sin(
                        (index * 0.7f) +
                                (animationProgress * 2f * PI.toFloat())
                    ) * 0.12f
                } else {
                    0f
                }

            val animatedAmplitude =
                (normalizedAmplitude + waveOffset)
                    .coerceIn(0.05f, 1f)

            val barHeight =
                minHeightPx +
                        ((maxHeightPx - minHeightPx) *
                                animatedAmplitude)

            val x =
                index * totalBarWidth +
                        (barWidthPx / 2f)

            val top =
                centerY - barHeight / 2f

            drawRoundRect(
                topLeft = Offset(
                    x = x,
                    y = top
                ),
                size = Size(
                    width = barWidthPx,
                    height = barHeight
                ),
                cornerRadius = CornerRadius(
                    x = barWidthPx,
                    y = barWidthPx
                ),
                brush = Brush.verticalGradient(
                    colors = gradientColors
                )
            )
        }
    }
}

@Composable
fun RadioWaveform(
    amplitudes: List<Float>,
    isPlaying: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {

        PremiumAudioWaveform(
            amplitudes = amplitudes,
            isPlaying = isPlaying,
            modifier = Modifier.fillMaxSize()
        )

        // Left fade
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(35.dp)
                .fillMaxHeight()
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            Color.Black,
                            Color.Transparent
                        )
                    )
                )
        )

        // Right fade
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(35.dp)
                .fillMaxHeight()
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black
                        )
                    )
                )
        )
    }
}

@Composable
@Preview(showBackground = true,     backgroundColor = 0xFF101018
)
fun run (modifier: Modifier = Modifier) {
    PremiumAudioWaveform(
        amplitudes = listOf(
            0.15f, 0.35f, 0.65f, 0.45f,
            0.85f, 0.55f, 0.30f, 0.75f,
            0.95f, 0.60f, 0.40f, 0.80f,
            0.25f, 0.50f, 0.90f, 0.70f,
            0.35f, 0.65f, 0.45f, 0.85f,
            0.55f, 0.30f, 0.75f, 0.95f,
            0.40f, 0.60f, 0.80f, 0.50f
        ),
        isPlaying = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp)
    )
}