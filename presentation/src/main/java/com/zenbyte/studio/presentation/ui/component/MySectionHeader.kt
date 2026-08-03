package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.ui.theme.buttonColor
import com.zenbyte.studio.presentation.viewmodel.utils.debounceClickable

@Composable
fun MySectionHeader(title: String, showSeeAll: Boolean = true, onClickSeeAll: (() -> Unit)? = null) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = adjustedFontSize(14f),
            ),
            modifier = Modifier.weight(1f)
        )

        if (showSeeAll) {
            Text(
                text = stringResource(R.string.see_all),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = adjustedFontSize(10f),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(color = buttonColor.copy(alpha = 0.5f))
                    .debounceClickable{
                        onClickSeeAll?.invoke()
                    }
                    .padding(horizontal = 8.dp, vertical = 3.dp)

            )

        }

    }
}

@Composable
@Preview
fun MySectionHeaderPreview(modifier: Modifier = Modifier) {
    MySectionHeader(title = "A2Z RADIO")
}
