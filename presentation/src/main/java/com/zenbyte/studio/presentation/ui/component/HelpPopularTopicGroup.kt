package com.zenbyte.studio.presentation.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.R

import androidx.compose.ui.res.stringResource
import com.zenbyte.studio.presentation.ui.theme.adjustedFontSize

@Composable
fun HelpPopularTopicGroup() {
    Column(modifier = Modifier.fillMaxWidth().border(
        width = 0.5.dp,
        color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.1f),
        shape = RoundedCornerShape(10.dp)
    ).padding(16.dp)) {
        SupportPopularTopic(
            icon = painterResource(R.drawable.music_svgrepo_com),
            title = stringResource(R.string.playback_issues),
            details = stringResource(R.string.playback_issues_details)
        )
        HeightGap(height = 10.dp)
        SupportPopularTopic(
            icon = painterResource(R.drawable.icon_crown),
            title = stringResource(R.string.premium_and_subscriptions),
            details = stringResource(R.string.premium_and_subscriptions_details)
        )
        HeightGap(height = 10.dp)
        SupportPopularTopic(
            icon = painterResource(R.drawable.recording_svgrepo_com),
            title = stringResource(R.string.record_music),
            details = stringResource(R.string.record_music_details)
        )
        HeightGap(height = 10.dp)
        SupportPopularTopic(
            icon = painterResource(R.drawable.icon_privacy),
            title = stringResource(R.string.privacy_and_security),
            details = stringResource(R.string.privacy_and_security_details)
        )
        HeightGap(height = 10.dp)
    }
}

@Composable
@Preview
fun HelpPopularTopicGroupPreview() {
    HelpPopularTopicGroup()
}