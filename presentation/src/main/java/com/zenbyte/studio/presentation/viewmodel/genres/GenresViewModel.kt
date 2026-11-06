package com.zenbyte.studio.presentation.viewmodel.genres

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyGenres
import com.zenbyte.studio.domain.usecase.local.LocalChannelUseCase
import com.zenbyte.studio.presentation.viewmodel.utils.Extras.getSimCountry
import com.zenbyte.studio.presentation.viewmodel.utils.MyCustomLogger
import com.zenbyte.studio.presentation.viewmodel.utils.localDataSources.GenresData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import kotlin.text.ifEmpty
import kotlin.text.uppercase

private const val TAG = "GenresViewModel"
@HiltViewModel
class GenresViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val localChannelUseCase: LocalChannelUseCase
) : ViewModel() {

    var genresData by mutableStateOf<List<MyGenres>>(emptyList())
    init {
        getGenresData()
    }


    fun getGenresData() {
        viewModelScope.launch {
            MyCustomLogger.logMessageInfo(TAG, "getGenresData: ${context.getSimCountry().ifEmpty { "USA" }}")
            genresData = GenresData.getGenres(context = context, localChannelUseCase = localChannelUseCase, scope = viewModelScope, country = context.getSimCountry().ifEmpty { "USA" }.uppercase())
        }
    }

}