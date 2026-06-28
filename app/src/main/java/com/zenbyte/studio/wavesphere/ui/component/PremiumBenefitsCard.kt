package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.ui.component.HeightGap
import com.zenbyte.studio.wavesphere.R

@Composable
fun PremiumBenefitsCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.1f),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
    ) {
        PremiumBenefitsItem(
            icon = painterResource(R.drawable.ad_svgrepo_com),
            title = stringResource(R.string.ad_free_experience),
            details = stringResource(R.string.ad_free_experience_details)
        )

        HeightGap(height = 5.dp)

        PremiumBenefitsItem(
            icon = painterResource(R.drawable.heart_tick_svgrepo_com),
            title = stringResource(R.string.unlimited_favorites),
            details = stringResource(R.string.unlimited_favorites_details)
        )

        HeightGap(height = 5.dp)

        PremiumBenefitsItem(
            icon = painterResource(R.drawable.play_stream_svgrepo_com),
            title = stringResource(R.string.background_play),
            details = stringResource(R.string.background_play_details)
        )

        HeightGap(height = 5.dp)

        PremiumBenefitsItem(
            icon = painterResource(R.drawable.icon_clock),
            title = stringResource(R.string.sleep_time),
            details = stringResource(R.string.sleep_timer_details)
        )

        HeightGap(height = 5.dp)

        PremiumBenefitsItem(
            icon = painterResource(R.drawable.high_definition_svgrepo_com),
            title = stringResource(R.string.high_quality_audio),
            details = stringResource(R.string.high_quality_audio_details)
        )
    }
}

@Composable
@Preview
fun PremiumBenefitsCardPreview(){
    PremiumBenefitsCard()
}