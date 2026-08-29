package com.zenbyte.studio.presentation.ui.component

import android.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.ui.theme.buttonColor
import com.zenbyte.studio.presentation.viewmodel.utils.rememberDebouncedClick

@Composable
fun SettingItem(
    appVersionCode : String = "",
    selectedLanguages : String = "",
    title: String,
    icon: Painter,
    showToggle: Boolean,
    isPremium: Boolean,
    showArrow: Boolean = false,
    setToggleButton : Boolean = false,
    enableChangeLanguage : Boolean = false,
    onCLickPremium: (() -> Unit)? = null,
    onToggleChanged: ((Boolean) -> Unit)? = null,
    onClick: () -> Unit,

) {

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = icon,
            contentDescription = null
        )
        WidthGap(width = 10.dp)
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
        WidthGap(width = 10.dp)
        if (showToggle) {
            Switch( modifier = Modifier.scale(0.8f), colors = SwitchColors(
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
                disabledCheckedBorderColor =androidx.compose.ui.graphics.Color.Unspecified,
                disabledCheckedIconColor = androidx.compose.ui.graphics.Color.Unspecified,
                disabledUncheckedThumbColor = androidx.compose.ui.graphics.Color.Unspecified,
                disabledUncheckedTrackColor = androidx.compose.ui.graphics.Color.Unspecified,
                disabledUncheckedBorderColor = androidx.compose.ui.graphics.Color.Unspecified,
                disabledUncheckedIconColor = androidx.compose.ui.graphics.Color.Unspecified
            ), checked = setToggleButton, onCheckedChange = {
                onToggleChanged?.invoke(it)
            })
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isPremium) {
                    PremiumTag {
                        onCLickPremium?.invoke()
                    }
                    IconButton(onClick = {}) {
                        Icon(painterResource(R.drawable.icon_arrow_next), contentDescription = null)
                    }
                }
            }
        }

        if (showArrow) {
            IconButton(onClick = rememberDebouncedClick {
                onClick.invoke()
            }) {
                Icon(painterResource(R.drawable.icon_arrow_next), contentDescription = null)
            }
        }
        if(appVersionCode.isNotBlank()){
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "V",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.W500,
                        fontSize = adjustedFontSize(8f)
                    ),
                    modifier = Modifier.padding(bottom = 2.dp)
                        .alignByBaseline()
                )

                Text(
                    text = appVersionCode,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.primary,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .alignByBaseline()
                        .padding(end = 16.dp)
                )
            }


        }

        if(enableChangeLanguage){
            ChangeLanguageButton(selectedLanguages = selectedLanguages){
                onClick.invoke()
            }
        }
    }
}