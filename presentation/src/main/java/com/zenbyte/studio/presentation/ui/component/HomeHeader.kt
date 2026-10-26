package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.viewmodel.home.HomeViewModel

@Composable
fun HomeHeader(viewModel: HomeViewModel) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = viewModel.timeOfDay,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                WidthGap(width = 5.dp)
                Image(
                    painter = painterResource(R.drawable.icon_emoji_happy),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            }

            HeightGap(height = 4.dp)
            Text(
                text = stringResource(R.string.home_header_subtitle),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    fontSize = adjustedFontSize(10f)
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }

        IconButton(
            onClick = {}
        ) {
            Icon(painter = painterResource(R.drawable.icon_bell), contentDescription = null)
        }
    }
}

@Composable
@Preview
fun HomeHeaderPreview(modifier: Modifier = Modifier) {
 //   HomeHeader(viewModel)
}