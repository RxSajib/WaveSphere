package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.ui.theme.buttonColor
import com.zenbyte.studio.presentation.viewmodel.utils.debounceClickable

@Composable
fun SupportHelpBanner() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .border(
                    width = 0.5.dp,
                    color = buttonColor.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = buttonColor.copy(alpha = 0.1f))

                .padding(7.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.about_svgrepo_com),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(color = buttonColor)
            )
        }
        WidthGap(width = 10.dp)
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.still_need_help),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = stringResource(R.string.still_need_help_details),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    fontWeight = FontWeight.W400,
                    fontSize = adjustedFontSize(10f)
                )
            )
        }
        Image(
            painter = painterResource(R.drawable.support),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
    }
}

@Composable
@Preview
fun SupportHelpBannerPreview(modifier: Modifier = Modifier) {
    SupportHelpBanner()
}