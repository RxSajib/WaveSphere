package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.wavesphere.ui.theme.buttonColor

@Composable
fun NowPlayingComponent(context: PlatformContext) {
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
        HeightSpace(height = 15.dp)
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data("https://www.shutterstock.com/image-vector/radio-channel-logo-vector-template-260nw-2121122948.jpg")
                    .size(500).build(),
                contentDescription = null,
                error = painterResource(R.drawable.applogowhite),
                placeholder = painterResource(R.drawable.applogowhite),
                modifier = Modifier
                    .size(80.dp)
                    .aspectRatio(1f)
                    .clip(shape = RoundedCornerShape(15.dp))
            )
            WidthSpace(width = 10.dp)
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
                Text(
                    text = "FOORTI 88.0 FM",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "Dhaka Bangladesh",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            WidthSpace(width = 5.dp)
            MyCustomPlayButton()
        }
    }
}

@Composable
@Preview
fun NowPlayingComponentPreview(modifier: Modifier = Modifier) {
    val context = LocalPlatformContext.current
    NowPlayingComponent(context)
}