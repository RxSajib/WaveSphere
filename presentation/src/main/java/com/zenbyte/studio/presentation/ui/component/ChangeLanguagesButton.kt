package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.zenbyte.studio.presentation.R

@Composable
fun ChangeLanguageButton(onChangeLanguage: () ->  Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "English",
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )
        IconButton(onClick = {
            onChangeLanguage.invoke()
        }) {
            Icon(painter = painterResource(R.drawable.icon_arrow_next), contentDescription = null)
        }
    }
}

