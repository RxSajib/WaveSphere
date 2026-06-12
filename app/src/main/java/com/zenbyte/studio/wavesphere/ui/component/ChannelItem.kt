package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.PlatformContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.wavesphere.ui.theme.adjustedFontSize

import androidx.compose.runtime.remember

@Composable
fun ChannelItem(modifier: Modifier, context: PlatformContext, myChannel: MyChannel) {
    val randomColor = remember(myChannel.stationuuid) {
        Color(
            red = (150..230).random(),
            green = (150..230).random(),
            blue = (150..230).random()
        )
    }
    val itemShape = remember { RoundedCornerShape(10.dp) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(itemShape)
                .border(
                    width = 1.dp,
                    color =  MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.2f
                    ),
                    shape = itemShape
                ),
            contentAlignment = Alignment.Center
        ) {

            Box(modifier = Modifier.fillMaxWidth(.8f).aspectRatio(1f)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(if (myChannel.favicon.isEmpty() || myChannel.favicon == "null") randomColor else Color.Transparent)


               , contentAlignment = Alignment.Center){
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
                    error = painterResource(R.drawable.applogowhite),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }



        }



        WidthSpace(width = 16.dp)

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


        IconButton(

            onClick = {},

            ) {
            Icon(
                painter = painterResource(R.drawable.icon_play_circle_svgrepo_com),
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