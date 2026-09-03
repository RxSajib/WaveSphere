package com.zenbyte.studio.presentation.ui.component

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.viewmodel.utils.localDataSources.AudioQualityData.getAudioQualityData

@Composable
fun AudioQualityGroup(context: Context) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.audio_quality),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
         Text(
            text = stringResource(R.string.audio_quality),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(context.getAudioQualityData()){audioQuality ->
                AudioQualityItem(audioQuality = audioQuality)
            }
        }
    }
}

