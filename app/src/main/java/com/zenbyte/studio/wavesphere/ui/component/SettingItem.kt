package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.wavesphere.R

@Composable
fun SettingItem(
    title: String,
    icon: Painter,
    showToggle: Boolean,
    isPremium: Boolean,
    showArrow: Boolean = false,
    enableChangeLanguage : Boolean = false
) {

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = icon,
            contentDescription = null
        )
        WidthSpace(width = 10.dp)
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        WidthSpace(width = 10.dp)
        if (showToggle) {
            Switch(checked = true, onCheckedChange = {})
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isPremium) {
                    PremiumTag {

                    }
                    IconButton(onClick = {}) {
                        Icon(painterResource(R.drawable.icon_arrow_next), contentDescription = null)
                    }
                }
            }
        }

        if (showArrow) {
            IconButton(onClick = {}) {
                Icon(painterResource(R.drawable.icon_arrow_next), contentDescription = null)
            }
        }

        if(enableChangeLanguage){
            ChangeLanguageButton{

            }
        }
    }
}