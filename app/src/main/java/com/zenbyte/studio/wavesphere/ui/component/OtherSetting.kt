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
fun OtherSetting(modifier: Modifier = Modifier) {
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
            text = stringResource(R.string.other),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        HeightSpace(height = 10.dp)
        SettingItem(
            title = stringResource(R.string.recording),
            icon = painterResource(R.drawable.recording_svgrepo_com),
            showToggle = false,
            isPremium = true,
            showArrow = false
        )

        SettingItem(
            title = stringResource(R.string.languages),
            icon = painterResource(R.drawable.world_svgrepo_com),
            showToggle = false,
            isPremium = false,
            showArrow = false,
            enableChangeLanguage = true
        )

        SettingItem(
            title = stringResource(R.string.help_support),
            icon = painterResource(R.drawable.help_svgrepo_com),
            showToggle = false,
            isPremium = false,
            showArrow = true
        )

        SettingItem(
            title = stringResource(R.string.about),
            icon = painterResource(R.drawable.about_svgrepo_com),
            showToggle = false,
            isPremium = false,
            showArrow = true
        )
    }
}

@Composable
@Preview
fun OtherSettingPreview(modifier: Modifier = Modifier) {
    OtherSetting()
}