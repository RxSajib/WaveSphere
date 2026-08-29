package com.zenbyte.studio.presentation.viewmodel.languagesByChannel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.data.local.model.Languages
import com.zenbyte.studio.domain.usecase.local.LocalChannelUseCase
import com.zenbyte.studio.presentation.viewmodel.utils.localDataSources.LanguagesData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguagesByChannelViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val localChannelUseCase: LocalChannelUseCase
) : ViewModel() {

    private val languagesListByChannelMutableStateFlow = MutableStateFlow<List<Languages>>(emptyList())
    val languagesListByChannel = languagesListByChannelMutableStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            getLanguagesByChannel()
        }
    }

     suspend fun getLanguagesByChannel(){
        languagesListByChannelMutableStateFlow.update {
            LanguagesData.getLanguagesChannel(context = context, localChannelUseCase = localChannelUseCase, viewModelScope)
        }
    }
}