package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.wavesphere.ui.theme.adjustedFontSize
import com.zenbyte.studio.wavesphere.ui.theme.countryColor
import com.zenbyte.studio.wavesphere.ui.theme.genresColor
import com.zenbyte.studio.wavesphere.ui.theme.languagesColor
import com.zenbyte.studio.wavesphere.ui.theme.newsColor

@Composable
fun CategoryButton(
    modifier: Modifier,
    color: Color,
    icon: Painter,
    categoryTitle: String,
    onClickCategory: (String) -> Unit
) {

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = color.copy(alpha = 0.1f))
                .clickable{
                    onClickCategory.invoke(categoryTitle)
                }, contentAlignment = Alignment.Center
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(.6f)
                    .aspectRatio(1f),
                colorFilter = ColorFilter.tint(color = color)
            )
        }
        HeightSpace(height = 10.dp)
        Text(
            text = categoryTitle,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = adjustedFontSize(10f)
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun CategoryList(
    onClickCountry: (String) -> Unit,
    onClickLanguages: (String) -> Unit,
    onClickGenres: (String) -> Unit,
    onClickNews: (String) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        CategoryButton(
            modifier = Modifier.weight(1f),
            color = countryColor,
            icon = painterResource(R.drawable.world_svgrepo_com),
            categoryTitle = stringResource(R.string.country),
            onClickCategory = { category ->
                onClickCountry.invoke(category)
            })

        WidthSpace(10.dp)
        CategoryButton(
            modifier = Modifier.weight(1f),
            color = languagesColor,
            icon = painterResource(R.drawable.languages_svgrepo_com),
            categoryTitle = stringResource(R.string.languages),
            onClickCategory = { category ->
                onClickLanguages.invoke(category)
            })
        WidthSpace(10.dp)
        CategoryButton(
            modifier = Modifier.weight(1f),
            color = genresColor,
            icon = painterResource(R.drawable.music_svgrepo_com),
            categoryTitle = stringResource(R.string.genres),
            onClickCategory = { category ->
                onClickGenres.invoke(category)
            })
        WidthSpace(10.dp)
        CategoryButton(
            modifier = Modifier.weight(1f),
            color = newsColor,
            icon = painterResource(R.drawable.news_svgrepo_com),
            categoryTitle = stringResource(R.string.news),
            onClickCategory = { category ->
                onClickNews.invoke(category)
            })
    }

}

@Composable
@Preview
fun CategoryButtonPreview() {
    CategoryList(onClickCountry = {}, onClickLanguages = {}, onClickGenres = {}, onClickNews = {})
}