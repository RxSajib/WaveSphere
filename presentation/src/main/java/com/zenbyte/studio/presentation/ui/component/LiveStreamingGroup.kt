package com.zenbyte.studio.presentation.ui.component

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.R

@Composable
fun LiveStreamingGroup() {
    Column(
        modifier = Modifier
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.1f),
                shape = RoundedCornerShape(10.dp)
            )
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        Text(
            text = stringResource(R.string.live_stream_options),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )
        HeightGap(height = 16.dp)

        PlayBackSettingItem(
            icon = painterResource(R.drawable.radio_waves_svgrepo_com),
            title = stringResource(R.string.auto_reconnect),
            details = stringResource(R.string.automatically_reconnect_if_stream_lost)
        )
        HeightGap(height = 10.dp)
        PlayBackSettingItem(
            icon = painterResource(R.drawable.icon_play_hover_play),
            title = stringResource(R.string.continue_in_background),
            details = stringResource(R.string.keep_playing_when_app_is_in_background)
        )
        HeightGap(height = 10.dp)
        PlayBackSettingItem(
            icon = painterResource(R.drawable.info_circle_svgrepo_com),
            title = stringResource(R.string.show_live_indicator),
            details = stringResource(R.string.display_live_badge_on_live_stations)
        )
        HeightGap(height = 10.dp)
        PlayBackSettingItem(
            icon = painterResource(R.drawable.icon_clock),
            title = stringResource(R.string.buffering_timeout),
            details = stringResource(R.string.stop_playback_if_buffering_take_too_long)
        )
    }
}

@Composable
@Preview
fun LiveStreamingGroupPreview() {
    LiveStreamingGroup()
}