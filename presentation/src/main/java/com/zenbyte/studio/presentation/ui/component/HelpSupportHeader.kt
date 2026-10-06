package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.R

@Composable
fun HelpSupportHeader() {
    Row(modifier = Modifier
        .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.hi_there),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            )
            HeightGap(height = 5.dp)
            Text(
                text = stringResource(R.string.help_support_details),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    fontWeight = FontWeight.W400
                )
            )
        }
        WidthGap(width = 10.dp)
        Image(
            painter = painterResource(R.drawable.help_support),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )

    }
}

@Composable
@Preview
fun HelpSupportHeaderPreview() {
    HelpSupportHeader()
}