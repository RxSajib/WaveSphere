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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.viewmodel.utils.MyCustomLogger
import com.zenbyte.studio.presentation.viewmodel.utils.debounceClickable

private const val TAG = "UpNextStationCard"
@Composable
fun UpNextStationCard(index : Int, myChannel: List<MyChannel>, onClick: () -> Unit) {

    val context = LocalContext.current
    MyCustomLogger.logMessageInfo(tag = TAG, message = "index $index")
    MyCustomLogger.logMessageInfo(tag = TAG, message = "list size ${myChannel.size}")

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
            .background(
                color = MaterialTheme.colorScheme.primary.copy(
                    alpha = 0.01f
                )
            )
            .debounceClickable {
                onClick.invoke()
            }
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
                        .data(if(myChannel.size > index+1) myChannel[index+1].favicon.takeIf { it.isNotEmpty() && it != "null" } else myChannel[index].favicon.takeIf { it.isNotEmpty() && it != "null" })
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
                text = if(myChannel.size > index+1) stringResource(R.string.up_next) else stringResource(R.string.current_playing),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = adjustedFontSize(8f),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                )
            )
            HeightGap(height = 10.dp)
            Text(
                text = if(myChannel.size > index+1) myChannel[index+1].name else myChannel[index].name,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = if(myChannel.size > index+1) myChannel[index+1].tags else myChannel[index].tags,
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

