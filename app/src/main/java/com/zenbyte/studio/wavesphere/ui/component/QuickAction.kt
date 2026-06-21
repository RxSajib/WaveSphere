package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.wavesphere.R
import com.zenbyte.studio.wavesphere.ui.theme.adjustedFontSize
import com.zenbyte.studio.wavesphere.ui.theme.genresColor

@Composable
fun QuickAction(modifier: Modifier, icon: Painter, title: String, isPremium: Boolean = false,
                onClick: () -> Unit) {

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = Modifier

                .clip(shape = RoundedCornerShape(8.dp))
                .clickable {
                    onClick.invoke()
                }
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            HeightSpace(height = 5.dp)
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = adjustedFontSize(8f),
                    color = MaterialTheme.colorScheme.primary
                )
            )
            HeightSpace(height = 2.dp)
            if (isPremium) {
                Text(
                    text = stringResource(R.string.premium),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = adjustedFontSize(5f),
                        color = genresColor
                    ),
                    modifier = Modifier
                        .border(
                            width = 0.5.dp,
                            color = genresColor,
                            shape = CircleShape
                        )
                        .padding(horizontal = 5.dp, vertical = 2.dp)
                )
            }
        }
    }


}