package com.zenbyte.studio.presentation.viewmodel.genres

import android.content.Context
import androidx.lifecycle.ViewModel
import com.zenbyte.studio.domain.model.MyGenres
import com.zenbyte.studio.presentation.viewmodel.utils.localDataSources.GenresData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    @ApplicationContext val context: Context
) : ViewModel() {

    init {
        getGenresData()
    }

    fun getGenresData() : List<MyGenres>{
        return GenresData.getGenres(context = context)
    }

}