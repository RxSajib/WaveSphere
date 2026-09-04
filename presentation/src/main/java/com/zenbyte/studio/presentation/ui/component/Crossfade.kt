package com.zenbyte.studio.presentation.ui.component

import android.widget.Spinner
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.ui.theme.buttonColor
import dev.vivvvek.seeker.Seeker

@Composable
fun Crossfade() {
    Column(modifier = Modifier.fillMaxWidth().border(
        width = 0.5.dp,
        color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.1f),
        shape = RoundedCornerShape(10.dp)
    ).fillMaxWidth().padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.crossfade),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                Text(
                    text = stringResource(R.string.smooth_transition_between_songs),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.W400,
                        fontSize = adjustedFontSize(10f),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                    )
                )
            }

            WidthGap(width = 10.dp)
            Switch(
                modifier = Modifier.scale(0.8f), colors = SwitchColors(
                    checkedThumbColor = androidx.compose.ui.graphics.Color.White,
                    checkedTrackColor = buttonColor,
                    checkedBorderColor = buttonColor,
                    checkedIconColor = androidx.compose.ui.graphics.Color.Unspecified,
                    uncheckedThumbColor = androidx.compose.ui.graphics.Color.White,
                    uncheckedTrackColor = androidx.compose.ui.graphics.Color.Gray,
                    uncheckedBorderColor = androidx.compose.ui.graphics.Color.Unspecified,
                    uncheckedIconColor = androidx.compose.ui.graphics.Color.Unspecified,
                    disabledCheckedThumbColor = androidx.compose.ui.graphics.Color.Unspecified,
                    disabledCheckedTrackColor = androidx.compose.ui.graphics.Color.Unspecified,
                    disabledCheckedBorderColor = androidx.compose.ui.graphics.Color.Unspecified,
                    disabledCheckedIconColor = androidx.compose.ui.graphics.Color.Unspecified,
                    disabledUncheckedThumbColor = androidx.compose.ui.graphics.Color.Unspecified,
                    disabledUncheckedTrackColor = androidx.compose.ui.graphics.Color.Unspecified,
                    disabledUncheckedBorderColor = androidx.compose.ui.graphics.Color.Unspecified,
                    disabledUncheckedIconColor = androidx.compose.ui.graphics.Color.Unspecified
                ), checked = true, onCheckedChange = {
                    //  onToggleChanged?.invoke(it)
                })
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "0s",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = adjustedFontSize(10f)
                )
            )
            Seeker(modifier = Modifier.weight(1f), value = 0.5f, onValueChange = {
            })

            WidthGap(width = 10.dp)
            Text(
                text = "12s",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = adjustedFontSize(10f)
                )
            )
            WidthGap(width = 10.dp)
            Text(
                text = "5s",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
            )
        }
    }
}


@Composable
@Preview
fun CrossfadePreview() {
    Crossfade()
}