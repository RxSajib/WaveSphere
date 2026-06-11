package com.zenbyte.studio.wavesphere.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.wavesphere.R

@Composable
fun GeneralSetting(modifier: Modifier) {
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
        Text(
            text = stringResource(R.string.general),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        HeightSpace(height = 10.dp)
        SettingItem(
            title = stringResource(R.string.dark_mode),
            icon = painterResource(R.drawable.moon_svgrepo_com),
            showToggle = true,
            isPremium = false
        )

        SettingItem(
            title = stringResource(R.string.data_saver),
            icon = painterResource(R.drawable.icon_wifi),
            showToggle = true,
            isPremium = false
        )

        SettingItem(
            title = stringResource(R.string.playback_setting),
            icon = painterResource(R.drawable.icon_play_hover_play),
            showToggle = false,
            isPremium = false,
            showArrow = true
        )

        SettingItem(
            title = stringResource(R.string.equlizer),
            icon = painterResource(R.drawable.audio_equlizer),
            showToggle = false,
            isPremium = true,
            showArrow = false
        )

        SettingItem(
            title = stringResource(R.string.sleep_time),
            icon = painterResource(R.drawable.icon_clock),
            showToggle = false,
            isPremium = true,
            showArrow = false
        )
    }
}

@Composable
@Preview
fun GeneralSettingPreview() {
    GeneralSetting(modifier = Modifier)
}