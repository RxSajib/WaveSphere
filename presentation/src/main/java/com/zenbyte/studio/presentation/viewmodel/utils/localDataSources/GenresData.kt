package com.zenbyte.studio.presentation.viewmodel.utils.localDataSources

import android.content.Context
import androidx.compose.ui.graphics.toArgb
import com.zenbyte.studio.domain.model.MyGenres
import com.zenbyte.studio.presentation.R
import com.zenbyte.studio.presentation.viewmodel.utils.Extras
import com.zenbyte.studio.presentation.viewmodel.utils.enum.GenresEnum

object GenresData {

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