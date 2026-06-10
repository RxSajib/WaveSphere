package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.wavesphere.ui.theme.buttonColor

@Composable
fun LiveTag() {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(5.dp))
            .border(width = 1.dp, color = buttonColor.copy(alpha = 0.5f), shape = RoundedCornerShape(5.dp))
            .background(color = buttonColor.copy(alpha = 0.1f))
            .padding(horizontal = 8.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(5.dp)
                .clip(shape = CircleShape)
                .background(color = Color.Red)
        )

        WidthSpace(width = 5.dp)
        Text(
            text = stringResource(R.string.live),
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview
fun LiveButtonPreview(modifier: Modifier = Modifier) {
    LiveTag()
}