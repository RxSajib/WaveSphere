package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.wavesphere.ui.theme.adjustedFontSize

@Composable
fun EmptyChannel() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(R.drawable.icon_favorite_heart_hover_pinch),
            modifier = Modifier.size(150.dp),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
        )
        Text(
            text = stringResource(R.string.nothing_saved),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = stringResource(R.string.nothing_saved_details),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = adjustedFontSize(10f),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
            )
        )
    }
}