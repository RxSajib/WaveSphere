package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.PlatformContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.ui.theme.genresColor

@Composable
fun MyCustomStation(
    isSelected: Boolean = false,
    context: PlatformContext,
    myChannel: MyChannel,
    onClick: (MyChannel) -> Unit
) {

    val randomColor = remember(myChannel.stationuuid) {
        Color(
            red = (150..230).random(),
            green = (150..230).random(),
            blue = (150..230).random()
        )
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f), contentAlignment = Alignment.Center
        ) {



            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        width = if (isSelected) 3.dp else 1.dp,
                        color = if (isSelected) genresColor else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.2f
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        onClick.invoke(myChannel)
                    },
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(.8f)
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
                        error = painterResource(R.drawable.applogowhite),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }

            }
            if (isSelected) {

                MusicPlayingIndicator(modifier = Modifier.align(Alignment.TopEnd).padding(10.dp))

            }

        }

        HeightGap(height = 10.dp)
        Text(
            text = myChannel.name,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = adjustedFontSize(10f)
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = myChannel.tags,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                fontWeight = FontWeight.W400,
                fontSize = adjustedFontSize(8f)
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

