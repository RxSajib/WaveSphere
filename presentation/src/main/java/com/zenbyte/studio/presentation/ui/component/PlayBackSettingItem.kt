package com.zenbyte.studio.presentation.ui.component

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.ui.theme.buttonColor
import com.zenbyte.studio.presentation.viewmodel.utils.debounceClickable

@Composable
fun PlayBackSettingItem(
    icon: Painter,
    title: String,
    details: String,
    switchValue: Boolean = true,
    isEnableBufferingTime: Boolean = false,
    onSwitchChanged: (Boolean) -> Unit = {}
) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .border(
                    width = 0.5.dp,
                    color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.2f))
                .padding(7.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        WidthGap(width = 10.dp)
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = details,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    fontSize = adjustedFontSize(10f)
                )
            )
        }
        WidthGap(width = 5.dp)
        if (isEnableBufferingTime) {
            Row(
                modifier = Modifier
                    .border(
                        width = 0.5.dp,
                        color = MaterialTheme.colorScheme.inverseSurface,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.01f)).debounceClickable{

                    }
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "30 Seconds",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = adjustedFontSize(12f),
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colorScheme.primary
                    )
                )

                WidthGap(width = 5.dp)
                Icon(
                    painter = painterResource(R.drawable.arrow_down),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        } else {
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
                ), checked = switchValue, onCheckedChange = {
                    onSwitchChanged.invoke(it)
                })
        }

    }
}

@Composable
@Preview
fun PlayBackSettingItemPreview() {
    PlayBackSettingItem(
        icon = painterResource(R.drawable.about_svgrepo_com),
        title = "Auto Reconnect",
        details = "Automatically reconnect to the network when disconnected"
    )
}