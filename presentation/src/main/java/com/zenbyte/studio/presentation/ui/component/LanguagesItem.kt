package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.domain.model.AppLanguages
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.ui.theme.buttonColor
import com.zenbyte.studio.presentation.viewmodel.utils.debounceClickable

@Composable
fun LanguagesItem(appLanguages: AppLanguages, isSelected: Boolean = false, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = CircleShape)
            .border(
                width = if (isSelected) 1.dp else 0.8.dp,
                color = if(isSelected) buttonColor.copy(alpha = 0.4f) else MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                shape = CircleShape
            )
            .debounceClickable {
                onClick.invoke()
            }
            .background(if (isSelected) buttonColor.copy(alpha = 0.1f) else Color.Transparent)

            .padding(start = 20.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = appLanguages.nativeName,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.W500,
                    fontSize = adjustedFontSize(12f)
                )
            )
            Text(
                text = appLanguages.name,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = if (isSelected) MaterialTheme.colorScheme.primary.copy(1f) else MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.5f
                    ),
                    fontSize = adjustedFontSize(10f)
                )
            )
        }

        RadioButton(
            colors = RadioButtonDefaults.colors(
                selectedColor = buttonColor,
                unselectedColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            ), selected = isSelected, onClick = {
                onClick.invoke()
            })
    }
}


