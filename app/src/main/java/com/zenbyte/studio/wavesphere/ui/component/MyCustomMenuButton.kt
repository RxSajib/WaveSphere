package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.wavesphere.ui.theme.buttonColor
import com.zenbyte.studio.wavesphere.ui.theme.textColorDark

@Composable
fun MyCustomMenuButton(
    modifier: Modifier,
    isSelected: Boolean = false,
    title: String,
    onClick: () -> Unit
) {

    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall.copy(
            fontWeight = if (isSelected) FontWeight.W500 else FontWeight.W400,
            color = if (isSelected) buttonColor else MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(if (isSelected) buttonColor.copy(alpha = 0.2f) else Color.Transparent)
            .border(
                width = if (isSelected) 0.5.dp else 0.dp,
                color = if (isSelected) buttonColor.copy(alpha = 0.2f) else Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                onClick.invoke()
            }
            .padding(horizontal = 10.dp, vertical = 5.dp),
        textAlign = TextAlign.Center

    )

}

@Composable
@Preview
fun MyCustomMenuButtonPreview(modifier: Modifier = Modifier) {
    MyCustomMenuButton(
        modifier = Modifier,
        isSelected = false,
        title = "Countries",
        onClick = {}
    )
}