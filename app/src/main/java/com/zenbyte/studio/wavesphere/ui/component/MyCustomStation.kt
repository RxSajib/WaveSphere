package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.PlatformContext
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.wavesphere.ui.theme.adjustedFontSize

@Composable
fun MyCustomStation(context: PlatformContext) {
    Column() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f), contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .border(
                        1.dp,
                        color = MaterialTheme.colorScheme.primary.copy(0.1f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth(.85f)
                    .aspectRatio(1f),
                model = ImageRequest.Builder(context)
                    .data("https://static.vecteezy.com/system/resources/previews/029/926/143/non_2x/podcast-icon-like-on-air-live-podcast-badge-icon-stamp-logo-radio-broadcasting-or-streaming-illustration-vector.jpg")
                    .size(500).build(),

                contentDescription = null,
                error = painterResource(R.drawable.applogowhite),
                placeholder = painterResource(R.drawable.applogowhite)
            )


        }

        HeightSpace(height = 10.dp)
        Text(
            text = "Radio Today",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = "89.0 FM",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                fontWeight = FontWeight.W400,
                fontSize = adjustedFontSize(10f)
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview
fun MyCustomStationPreview(modifier: Modifier = Modifier) {
    val context = LocalPlatformContext.current
    MyCustomStation(context = context)
}