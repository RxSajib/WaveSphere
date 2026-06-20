package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.zenbyte.studio.wavesphere.ui.theme.adjustedFontSize
import com.zenbyte.studio.wavesphere.ui.theme.buttonColor
import java.util.Locale
import java.util.Locale.getDefault
import androidx.compose.ui.platform.LocalLocale
import com.zenbyte.studio.wavesphere.ui.theme.genresColor

@Composable
fun SubscriptionPlanCardItem(
    price: String,
    duration: String,
    savePercentage: String,
    note: String?= null,
    isDurationSelected: Boolean,
    isSelected: Boolean,
    onClickListener: () -> Unit
) {

    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = buttonColor.copy(alpha = 0.1f))
            .padding(10.dp)
    ) {
        Text(text = duration,
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = adjustedFontSize(10f),
                color = buttonColor,
                fontWeight = FontWeight.W400
            ))

        HeightSpace(height = 10.dp)

        val constraintSet = ConstraintSet {
            val price = createRefFor("price")
            val duration = createRefFor("duration")

            constrain(duration) {
                bottom.linkTo(price.bottom)
                start.linkTo(price.end)
            }
        }


        ConstraintLayout(constraintSet = constraintSet) {
            Text(text = "$$price",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                ),
                modifier = Modifier.layoutId("price")

            )
            Text(
                text = "/${duration.lowercase(LocalLocale.current.platformLocale)}",
                modifier = Modifier.layoutId("duration").padding(start = 3.dp),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    fontSize = adjustedFontSize(8f)
                ),
            )
        }

        note?.let {note ->
            Column() {
                HeightSpace(height = 10.dp)
                Text(text = note,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = adjustedFontSize(10f),
                        color = genresColor,
                        fontWeight = FontWeight.W400
                    ))
            }

        }

    }

}

@Composable
@Preview(showBackground = false)
fun SubscriptionPlanCardItemPreview() {
    SubscriptionPlanCardItem(
        price = "500",
        duration = "Month",
        savePercentage = "35",
        note = "Best value",
        isDurationSelected = false,
        isSelected = true,
        onClickListener = {}
    )
}