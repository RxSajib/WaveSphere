package com.zenbyte.studio.presentation.ui.component

import android.content.Context
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.ui.theme.buttonColor
import com.zenbyte.studio.presentation.viewmodel.utils.debounceClickable
import kotlin.Boolean

@Composable
fun MyPlayerSnackBar(
    myChannel: MyChannel,
    context: Context,
    isBuffering: Boolean = false,
    isPlaying: Boolean = false,
) {
    val randomColor = remember(myChannel.stationuuid) {
        Color(
            red = (150..230).random(),
            green = (150..230).random(),
            blue = (150..230).random()
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()   .clip(shape = RoundedCornerShape(10.dp))
            .background(color = buttonColor.copy(alpha = 0.15f))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .aspectRatio(1f)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(if (myChannel.favicon.isEmpty() || myChannel.favicon == "null") randomColor else Color.Transparent),
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

        WidthGap(width = 16.dp)

        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = myChannel.name.trim(),
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            HeightGap(height = 2.dp)

            Text(
                text = myChannel.tags.trim(),
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

        Icon(
            painter = painterResource(R.drawable.applogo),
            modifier = Modifier.size(40.dp),
            contentDescription = null,
            tint = buttonColor
        )
        WidthGap(width = 10.dp)
        Box(
            modifier = Modifier
                .clip(shape = CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                    shape = CircleShape
                )
                .debounceClickable {
                    // onMediaController.invoke(myChannel)
                }
                .padding(5.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isBuffering) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(5.dp)
                        .size(15.dp),
                    trackColor = MaterialTheme.colorScheme.primary,
                    strokeWidth = 2.dp
                )
            } else {
                Icon(
                    painter = if (isPlaying) painterResource(R.drawable.pause) else painterResource(
                        R.drawable.ic_play
                    ),
                    contentDescription = null
                )
            }

        }
    }
}

@Preview
@Composable
private fun MyPlayerSnackBarPreview() {

    val channel = MyChannel(
        stationuuid = "abc123",
        name = "Radio Bangladesh",
        codec = "MP3",
        country = "Bangladesh",
        url = "https://example.com/radio",
        urlResolved = "https://example.com/radio",
        favicon = "",
        language = "Bengali",
        votes = 125,
        tags = "bangla, music, news",
        lastcheckok = 1,
        sslError = 0
    )

    MaterialTheme {
        MyPlayerSnackBar(
            myChannel = channel,
            context = LocalContext.current,
            isBuffering = false,
            isPlaying = true
        )
    }
}