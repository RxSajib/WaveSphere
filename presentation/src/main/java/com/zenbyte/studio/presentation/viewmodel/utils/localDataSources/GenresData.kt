package com.zenbyte.studio.presentation.viewmodel.utils.localDataSources

import android.content.Context
import androidx.compose.ui.graphics.toArgb
import androidx.core.util.toRange
import com.zenbyte.studio.data.local.model.Languages
import com.zenbyte.studio.domain.model.MyGenres
import com.zenbyte.studio.domain.usecase.local.LocalChannelUseCase
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.viewmodel.utils.Extras
import com.zenbyte.studio.presentation.viewmodel.utils.Extras.getGenresName
import com.zenbyte.studio.presentation.viewmodel.utils.enum.ChannelLanguages
import com.zenbyte.studio.presentation.viewmodel.utils.enum.GenresEnum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.serialization.InternalSerializationApi

object GenresData {

    suspend fun getGenres(context: Context, localChannelUseCase: LocalChannelUseCase, scope : CoroutineScope, country : String): List<MyGenres> {
        return GenresEnum.entries.map { genre ->
            MyGenres(
                titleEnglish = genre.name,
                title = context.getGenresName(genre),
                icon = Extras.getGenreIcon(genre),
                color = Extras.getGenreBackgroundColor(genre).toArgb(),
                stationsCount =  scope.async {localChannelUseCase.getChannelLengthByTags(tags = genre.name, country = country).first()}.await()
            )
        }
    }

}