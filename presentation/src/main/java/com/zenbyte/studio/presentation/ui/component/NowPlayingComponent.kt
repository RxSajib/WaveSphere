package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.PlatformContext
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.buttonColor
import com.zenbyte.studio.presentation.ui.theme.playIconColor
import com.zenbyte.studio.presentation.viewmodel.home.HomeViewModel

@Composable
fun NowPlayingComponent(context: PlatformContext, channel: MyChannel, viewModel: HomeViewModel) {
    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(color = buttonColor.copy(alpha = 0.03f))

            .border(
                width = 0.5.dp,
                color = buttonColor.copy(alpha = 0.2f),
                shape = RoundedCornerShape(10.dp)
            )

            .padding(15.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.now_playing),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            LiveTag()
        }
        HeightGap(height = 15.dp)
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(channel.favicon)
                    .size(500).build(),
                contentDescription = null,
                error = painterResource(R.drawable.applogowhite),
                placeholder = painterResource(R.drawable.applogowhite),
                modifier = Modifier
                    .size(80.dp)
                    .aspectRatio(1f)
                    .clip(shape = RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop
            )
            WidthGap(width = 10.dp)
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
                Text(
                    text = channel.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = channel.tags,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

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
                    isPlaying = viewModel.isMusicPlaying,
                    gradientColors = listOf(buttonColor, playIconColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                )
            }

            WidthGap(width = 5.dp)
            MyCustomPlayButton(isPlaying = viewModel.isMusicPlaying){
                viewModel.playPushController()
            }
        }
    }
}

@Composable
@Preview
fun NowPlayingComponentPreview(modifier: Modifier = Modifier) {
    val context = LocalPlatformContext.current
  //  NowPlayingComponent(context, channel)
}