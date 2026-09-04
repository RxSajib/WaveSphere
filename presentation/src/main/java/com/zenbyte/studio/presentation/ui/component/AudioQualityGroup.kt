package com.zenbyte.studio.presentation.ui.component

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.viewmodel.utils.localDataSources.AudioQualityData.getAudioQualityData

@Composable
fun AudioQualityGroup(context: Context) {
    Column(
        modifier = Modifier
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.1f),
                shape = RoundedCornerShape(10.dp)
            ).fillMaxWidth().padding(16.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.audio_quality),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                text = stringResource(R.string.choose_streaming_quality),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)

                )
            )
        }
        HeightGap(height = 10.dp)

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(context.getAudioQualityData()) { audioQuality ->
                AudioQualityItem(audioQuality = audioQuality)
            }
        }
    }
}

