package com.zenbyte.studio.presentation.ui.component

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize

@Composable
fun DownloadChannel(modifier: Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(30.dp), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize(), strokeWidth = 2.dp)
            Icon(
                painter = painterResource(com.revenuecat.purchases.ui.revenuecatui.R.drawable.download),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .aspectRatio(1f)
            )
        }
        WidthGap(width = 15.dp)
        Column(modifier = Modifier) {
            Text(
                text = "Fetch Total Channel: 521",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = adjustedFontSize(12f),
                    color = MaterialTheme.colorScheme.primary
                )
            )
            HeightGap(height = 5.dp)
            HorizontalDivider(
                modifier = Modifier
                    .height(0.5.dp)
                    .background(color = androidx.compose.ui.graphics.Color.Blue.copy(alpha = 0.5f))
                    .width(100.dp)
            )
            HeightGap(height = 5.dp)
            Text(
                text = "Getting stations list...",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.W300,
                    fontSize = adjustedFontSize(10f),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                )
            )
        }
    }
}

@Composable
@Preview
fun DownloadChannelPreview() {
    DownloadChannel(modifier = Modifier)
}
