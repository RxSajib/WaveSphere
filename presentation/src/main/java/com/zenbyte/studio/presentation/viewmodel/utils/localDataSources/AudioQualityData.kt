package com.zenbyte.studio.presentation.viewmodel.utils.localDataSources

import android.content.Context
import androidx.compose.ui.graphics.toArgb
import com.zenbyte.studio.data.local.model.AudioQuality
import com.zenbyte.studio.presentation.viewmodel.utils.Extras
import com.zenbyte.studio.presentation.viewmodel.utils.Extras.getAudioRateDetailsByBitRate
import com.zenbyte.studio.presentation.viewmodel.utils.Extras.getAudioRateTitleByBitRate
import com.zenbyte.studio.presentation.viewmodel.utils.enum.AudioBitRate
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first

object AudioQualityData {

    fun Context.getAudioQualityData(): List<AudioQuality> {
        return AudioBitRate.entries.map { audioBitRate ->
            AudioQuality(
                title = getAudioRateTitleByBitRate(bitRate = audioBitRate),
                details = getAudioRateDetailsByBitRate(bitRate = audioBitRate),
                isChecked = false,
                audioRate = if (audioBitRate.name == AudioBitRate.DEFAULT.name) null else audioBitRate.bitRate.toString()
            )
        }
    }
}