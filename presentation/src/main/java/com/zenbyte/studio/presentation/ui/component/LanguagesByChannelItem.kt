package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.data.local.model.Languages
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.viewmodel.utils.debounceClickable

@Composable
fun LanguagesByChannelItem(languages: Languages, onClick : (Languages) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color(languages.color).copy(alpha = 0.05f))
            .debounceClickable{
                onClick.invoke(languages)
            }
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(shape = CircleShape)
                .background(color = Color(languages.color).copy(alpha = 0.08f))
                .padding(10.dp), contentAlignment = Alignment.Center,
        ) {
            Text(
                text = languages.title.take(2).uppercase(),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color(languages.color).copy(alpha = 0.5f)
                )
            )
        }
        HeightGap(height = 10.dp)
        Text(
            text = languages.title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary
            )
        )
        Text(
            text = "${languages.stationsCount} Stations",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                fontSize = adjustedFontSize(10f)
            )
        )
    }
}