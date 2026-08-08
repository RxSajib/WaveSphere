package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.data.local.model.Genres
import com.zenbyte.studio.domain.model.MyGenres
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.viewmodel.utils.debounceClickable

@Composable
fun GenresItem(genres: MyGenres, onClick: (Genres) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color(genres.color).copy(alpha = 0.05f))
            .debounceClickable{

            }
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(shape = CircleShape)
                .background(color = Color(genres.color).copy(alpha = 0.08f))
                .padding(10.dp), contentAlignment = Alignment.Center,
        ) {
            Icon(painter = painterResource(genres.icon), contentDescription = null, tint = Color(genres.color).copy(0.8f),
                modifier = Modifier.size(24.dp))
        }
        HeightGap(height = 10.dp)
        Text(
            text = genres.title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary
            )
        )
        Text(
            text = "${genres.stationsCount} Stations",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                fontSize = adjustedFontSize(10f)
            )
        )
    }
}




