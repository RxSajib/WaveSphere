package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.wavesphere.ui.theme.adjustedFontSize
import com.zenbyte.studio.wavesphere.ui.theme.buttonColor

@Composable
fun MySectionHeader(title: String, showSeeAll: Boolean = true, onClick: (() -> Unit)? = null) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = adjustedFontSize(14f),
            ),
            modifier = Modifier.weight(1f)
        )

        if (showSeeAll) {
            TextButton(onClick = {
                onClick?.invoke()
            }) {
                Text(
                    text = stringResource(R.string.see_all),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = adjustedFontSize(10f),
                        color = buttonColor,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

    }
}

@Composable
@Preview
fun MySectionHeaderPreview(modifier: Modifier = Modifier) {
    MySectionHeader(title = "A2Z RADIO")
}
