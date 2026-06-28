package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize
import com.zenbyte.studio.presentation.ui.theme.buttonColor
import androidx.compose.ui.platform.LocalLocale
import com.zenbyte.studio.presentation.ui.component.HeightGap
import com.zenbyte.studio.presentation.ui.theme.genresColor

@Composable
fun SubscriptionPlanCardItem(
    modifier: Modifier,
    price: String,
    duration: String,
    title : String,
    savePercentage: String,
    note: String? = null,
    isDurationSelected: Boolean,
    isSelected: Boolean,
    onClickListener: () -> Unit
) {

    val borderColor by animateColorAsState(
        targetValue = if (isSelected) buttonColor else Color.Transparent,
        animationSpec = tween(durationMillis = 300), label = "borderColor"
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) buttonColor.copy(alpha = 0.15f) else buttonColor.copy(alpha = 0.05f),
        animationSpec = tween(durationMillis = 300), label = "backgroundColor"
    )

    val borderWidth by animateDpAsState(
        targetValue = if (isSelected) 1.dp else 0.5.dp,
        animationSpec = tween(durationMillis = 300), label = "borderWidth"
    )

    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.05f else 1f,
        animationSpec = tween(durationMillis = 300), label = "scale"
    )

    Column(
        modifier = modifier
            .scale(scale)
            .clip(shape = RoundedCornerShape(10.dp))
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(10.dp)
            )
            .background(color = backgroundColor)
            .clickable {
                onClickListener.invoke()
            }
            .padding(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                color = buttonColor,
                fontWeight = if(isSelected) FontWeight.Bold else FontWeight.W400
            )
        )

        HeightGap(height = 10.dp)

        val constraintSet = ConstraintSet {
            val price = createRefFor("price")
            val duration = createRefFor("duration")

            constrain(duration) {
                bottom.linkTo(price.bottom)
                start.linkTo(price.end)
            }
        }


        ConstraintLayout(constraintSet = constraintSet) {
            Text(
                text = "$$price",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = if(isSelected) FontWeight.Bold else FontWeight.W400
                ),
                modifier = Modifier.layoutId("price")

            )
            Text(
                text = "/${duration.lowercase(LocalLocale.current.platformLocale)}",
                modifier = Modifier
                    .layoutId("duration")
                    .padding(start = 3.dp),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    fontSize = adjustedFontSize(8f)
                ),
            )
        }

        note?.let { note ->
            Column() {
                HeightGap(height = 10.dp)
                Text(
                    text = note,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = adjustedFontSize(10f),
                        color = genresColor,
                        fontWeight = FontWeight.W400
                    )
                )
            }

        }

    }

}

@Composable
@Preview(showBackground = false)
fun SubscriptionPlanCardItemPreview() {
    SubscriptionPlanCardItem(
        modifier = Modifier,
        price = "500",
        duration = "Month",
        savePercentage = "35",
        note = "Best value",
        isDurationSelected = false,
        isSelected = true,
        title = "Monthly",
        onClickListener = {}
    )
}