package com.zenbyte.studio.presentation.ui.component

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.ui.theme.onPrimaryLight

@Composable
fun MyCustomButton(
    isEnable: Boolean = true,
    title: String,
    backgroundColor: Color = onPrimaryLight,
    textColor: Color = Color.White,
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit,
    padding: Dp = 6.dp,
    showProgress: Boolean = false,
    leftIcon : Painter? = null
) {

    Button(
        onClick = {

            onClickButton.invoke() },
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor,
            containerColor = if (isEnable) backgroundColor else Color.Gray
        ),
        modifier = modifier
    ) {

        leftIcon?.let {
            Icon(
                painter = it,
                contentDescription = null
            )
        }
        WidthGap(width = 4.dp)
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
