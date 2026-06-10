package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.PlatformContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.wavesphere.ui.theme.adjustedFontSize

@Composable
fun ChannelItem(context: PlatformContext, myChannel: MyChannel) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(65.dp),
            model = ImageRequest.Builder(context = context).data(myChannel.favicon)
                .size(500).build(),
            //   error = {},
            // placeholder = {},
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        WidthSpace(width = 10.dp)

        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = myChannel.name,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            HeightSpace(height = 2.dp)

            Text(
                text = myChannel.tags,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.W400,
                    fontSize = adjustedFontSize(10f),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                )
            )
        }

        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(R.drawable.icon_favorite_heart_hover_pinch),
                contentDescription = null
            )
        }
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(R.drawable.icon_play_hover_play),
                contentDescription = null
            )
        }

    }
}

@Composable
@Preview
fun ChannelItemPreview() {
   // ChannelItem(context = LocalPlatformContext.current,)
}