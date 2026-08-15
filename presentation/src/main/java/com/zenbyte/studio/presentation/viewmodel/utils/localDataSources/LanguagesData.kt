package com.zenbyte.studio.presentation.viewmodel.utils.localDataSources

import android.content.Context
import androidx.compose.ui.graphics.toArgb
import com.zenbyte.studio.data.local.model.Languages
import com.zenbyte.studio.presentation.viewmodel.utils.Extras
import com.zenbyte.studio.presentation.viewmodel.utils.enum.ChannelLanguages

object LanguagesData {

    fun getLanguagesChannel(context: Context) : List<Languages> {
        return ChannelLanguages.entries.map {channelLanguages ->
            Languages(
                title = Extras.getLanguagesName(languages = channelLanguages, context = context),
                titleEnglish = channelLanguages.name,
                color = Extras.getLanguagesColor(languages = channelLanguages).toArgb(),
                stationsCount = (1000..5000).random()
            )
        }
    }
}