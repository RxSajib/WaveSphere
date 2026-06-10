package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.PlatformContext
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import com.zenbyte.studio.wavesphere.R

@Composable
fun ChannelItem(context: PlatformContext) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = context).data("")
                .size(300).build(),
            //   error = {},
            // placeholder = {},
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        WidthSpace(width = 10.dp)

        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = "Dhaka FM",
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            HeightSpace(height = 5.dp)

            Text(
                text = "Mirpur Dhaka Bangladesh",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
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
    ChannelItem(context = LocalPlatformContext.current)
}