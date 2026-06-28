package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.ui.theme.buttonColor

@Composable
fun MyCustomButton(
    isEnable: Boolean = true,
    title: String,
    backgroundColor: Color = buttonColor,
    textColor: Color = Color.White,
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit,
    padding: Dp = 10.dp,
    showProgress: Boolean = false,
    leftIcon : Painter? = null
) {

    Button(
        onClick = { onClickButton() },
        enabled = isEnable && !showProgress,
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor,
            containerColor = if (isEnable) backgroundColor else Color.Gray
        ),
        modifier = modifier
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.W600,
                fontSize = adjustedFontSize(12f),
                color = if(isEnable) Color.White else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            ),
            modifier = Modifier.padding(padding),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
@Preview(showBackground = true)
fun MyCustomButtonPreview() {
    MyCustomButton(
        title = "Login",
        modifier = Modifier,
        onClickButton = {},
    )
}