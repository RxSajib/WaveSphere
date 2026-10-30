package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize

@Composable
fun UpNextStationCard(myChannel: MyChannel) {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.primary.copy(
                    alpha = 0.1f
                ),

                shape = RoundedCornerShape(10.dp)
            )
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colorScheme.primary.copy(
                alpha = 0.01f
            ))
            .padding(10.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.2f
                    ),
                    shape = RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .aspectRatio(1f)
                    .clip(shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp))
                        .aspectRatio(1f),
                    model = ImageRequest.Builder(context = context)
                        .data(myChannel.favicon.takeIf { it.isNotEmpty() && it != "null" })
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.applogo),
                    error = painterResource(R.drawable.applogo),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
        }
        WidthGap(width = 10.dp)
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "UP NEXT",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = adjustedFontSize(8f),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                )
            )
            HeightGap(height = 10.dp)
            Text(
                text = "Foorti 88.0 FM",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = "Dhaka Bangladesh",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = adjustedFontSize(8f),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                )
            )
        }
        WidthGap(width = 5.dp)
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(R.drawable.ic_menu_deep),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
@Preview
fun UpNextStationCardPreview() {
    UpNextStationCard(
        myChannel = MyChannel(
            stationuuid = "station-002",
            name = "Foorti 88.0 FM",
            codec = "MP3",
            country = "Bangladesh",
            url = "https://example.com/foorti",
            urlResolved = "https://example.com/foorti/stream",
            favicon = "https://example.com/foorti.png",
            language = "bengali",
            votes = 950,
            tags = "pop,music,entertainment",
            lastcheckok = 1,
            sslError = 0
        )
    )
}