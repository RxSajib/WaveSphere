package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.wavesphere.ui.theme.adjustedFontSize
import com.zenbyte.studio.wavesphere.ui.theme.genresColor

@Composable
fun PremiumTag() {
    Row(
        modifier = Modifier
            .border(
                width = 0.5.dp,
                color = genresColor,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.icon_crown),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = genresColor),
            modifier = Modifier.size(18.dp)
        )
        WidthSpace(width = 5.dp)
        Text(
            text = stringResource(R.string.premium),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.W500,
                fontSize = adjustedFontSize(8f),
                color = genresColor
            )
        )
    }
}

@Composable
@Preview
fun PremiumTagPreview() {
    PremiumTag()
}