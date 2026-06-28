package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.PlatformContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.zenbyte.studio.domain.model.MyCountry
import com.zenbyte.studio.presentation.ui.component.HeightGap
import com.zenbyte.studio.presentation.ui.component.WidthGap
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize

@Composable
fun CountryItem(context: PlatformContext, country: MyCountry?, onClickCountry: (MyCountry) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{
                onClickCountry.invoke(country!!)
            }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            modifier = Modifier
                .size(50.dp)
                .aspectRatio(1.5f),
            model = ImageRequest.Builder(context = context).data(country?.countryFlag)
                .crossfade(true).build(),
            placeholder = painterResource(R.drawable.applogo),
            error = painterResource(R.drawable.applogowhite),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        WidthGap(width = 16.dp)

        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = country?.name ?: "",
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary
                )
            )
            HeightGap(height = 2.dp)

            Text(
                text = "${country?.stationCount} ${stringResource(R.string.channel)}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.W400,
                    fontSize = adjustedFontSize(10f),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                )
            )
        }

        Image(
            painter = painterResource(R.drawable.icon_arrow_next), contentDescription = null,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
        )
    }
}
