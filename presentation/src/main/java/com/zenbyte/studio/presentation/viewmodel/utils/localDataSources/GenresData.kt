package com.zenbyte.studio.presentation.viewmodel.utils.localDataSources

import android.content.Context
import androidx.compose.ui.graphics.toArgb
import androidx.core.util.toRange
import com.zenbyte.studio.data.local.model.Languages
import com.zenbyte.studio.domain.model.MyGenres
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.viewmodel.utils.Extras
import com.zenbyte.studio.presentation.viewmodel.utils.enum.ChannelLanguages
import com.zenbyte.studio.presentation.viewmodel.utils.enum.GenresEnum
import kotlinx.serialization.InternalSerializationApi

object GenresData {

    @OptIn(InternalSerializationApi::class)
    fun getGenres(context: Context): List<MyGenres> {
        return GenresEnum.entries.map { genre ->
            MyGenres(
                titleEnglish = genre.name,
                title = Extras.getGenresName(genre, context),
                icon = Extras.getGenreIcon(genre),
                color = Extras.getGenreBackgroundColor(genre).toArgb(),
                stationsCount = (1000..5000).random()
            )
        }
    }

}