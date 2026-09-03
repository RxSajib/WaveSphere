package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.data.local.model.AudioQuality
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.ui.theme.buttonColor

@Composable
fun AudioQualityItem(
    audioQuality: AudioQuality
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            colors = RadioButtonDefaults.colors(selectedColor = buttonColor),
            selected = audioQuality.isChecked,
            onClick = {

            })
        WidthGap(width = 10.dp)
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = audioQuality.title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = audioQuality.details,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    fontSize = adjustedFontSize(10f)
                )
            )
        }
        WidthGap(width = 10.dp)
        audioQuality.audioRate?.let { bitRate ->
            Text(
                text = "$bitRate kbps",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = adjustedFontSize(8f),
                    color = buttonColor
                ),
                modifier = Modifier
                    .border(
                        width = 0.5.dp, color =
                            buttonColor,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(horizontal = 5.dp, vertical = 2.dp)
            )
        } ?: Image(
            painter = painterResource(R.drawable.applogo),
            contentDescription = null,
            modifier = Modifier.size(30.dp),
            colorFilter = ColorFilter.tint(color = buttonColor)
        )
    }

}