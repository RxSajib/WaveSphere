package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.wavesphere.ui.theme.adjustedFontSize
import com.zenbyte.studio.wavesphere.ui.theme.buttonColor
import com.zenbyte.studio.wavesphere.ui.theme.genresColor

@Composable
fun PremiumStatusCard(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.1f),
                shape = RoundedCornerShape(10.dp)
            ).padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.premium_lowercase),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        HeightSpace(height = 10.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = genresColor.copy(alpha = 0.05f))
                .border(
                    width = 0.5.dp,
                    color = genresColor.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.crown),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            WidthSpace(width = 10.dp)

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
                Text(
                    text = stringResource(R.string.you_are_premium),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = genresColor
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = stringResource(R.string.thanks_for_supporting_wavesphere),
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                        fontWeight = FontWeight.W400,
                        fontSize = adjustedFontSize(10f)
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}

@Composable
@Preview
fun PremiumStatusCardPreview() {
    PremiumStatusCard(modifier = Modifier)
}