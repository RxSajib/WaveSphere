package com.zenbyte.studio.presentation.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.ui.theme.buttonColor

@Composable
fun SupportPopularTopic(icon: Painter, title: String, details: String, issueDetails : String) {

    var isExpanded by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) 90f else 0f,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        label = "rotation"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .border(
                        width = 0.5.dp,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(7.dp)
            ) {
                Image(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(color = buttonColor)
                )
            }
            WidthGap(width = 10.dp)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = details,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                        fontWeight = FontWeight.W400,
                        fontSize = adjustedFontSize(10f)
                    )
                )
            }
            IconButton(onClick = {
                isExpanded = !isExpanded
            }) {
                Icon(
                    painter = painterResource(R.drawable.icon_arrow_next),
                    modifier = Modifier.rotate(rotation),
                    contentDescription = null
                )
            }
        }

        AnimatedVisibility(visible = isExpanded) {
            Column {
                HeightGap(height = 10.dp)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 0.5.dp,
                            color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.05f))
                        .padding(10.dp)
                ) {
                    Text(
                        text = issueDetails,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontSize = adjustedFontSize(8f),
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun SupportPopularTopicPreview() {
    SupportPopularTopic(
        icon = painterResource(R.drawable.icon_bell),
        title = "Playback Issue",
        details = "Fix buffering, playback or streaming issues",
        issueDetails = "• Check your internet connection. • Switch between Wi-Fi and mobile data. • Make sure your connection is stable. • Stop the station and play it again. • Wait a few seconds for the stream to load. • Try playing another radio station. • Check if the station is currently online. • Close and reopen the app. • Keep the app updated. • If the issue continues, contact support."
    )
}