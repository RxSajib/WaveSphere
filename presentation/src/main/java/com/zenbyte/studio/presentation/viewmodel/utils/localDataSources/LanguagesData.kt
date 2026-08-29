package com.zenbyte.studio.presentation.viewmodel.utils.localDataSources

import android.content.Context
import androidx.compose.ui.graphics.toArgb
import com.zenbyte.studio.data.local.model.Languages
import com.zenbyte.studio.domain.usecase.local.LocalChannelUseCase
import com.zenbyte.studio.presentation.viewmodel.utils.Extras
import com.zenbyte.studio.presentation.viewmodel.utils.enum.ChannelLanguages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

object LanguagesData {

    suspend fun getLanguagesChannel(context: Context, localChannelUseCase: LocalChannelUseCase, coroutineScope : CoroutineScope) : List<Languages> {
        return ChannelLanguages.entries.map {channelLanguages ->
            Languages(
                title = Extras.getLanguagesName(languages = channelLanguages, context = context),
                titleEnglish = channelLanguages.name,
                color = Extras.getLanguagesColor(languages = channelLanguages).toArgb(),
                stationsCount = coroutineScope.async {
                    localChannelUseCase.getChannelLengthByLanguages(languages = channelLanguages.name).first()
                }.await()
            )
        }
    }
}