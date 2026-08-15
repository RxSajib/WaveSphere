package com.zenbyte.studio.presentation.viewmodel.languagesByChannel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.zenbyte.studio.data.local.model.Languages
import com.zenbyte.studio.presentation.viewmodel.utils.Extras
import com.zenbyte.studio.presentation.viewmodel.utils.localDataSources.LanguagesData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LanguagesByChannelViewModel @Inject constructor(
    @ApplicationContext val context: Context
) : ViewModel() {

    private val languagesListByChannelMutableStateFlow = MutableStateFlow<List<Languages>>(emptyList())
    val languagesListByChannel = languagesListByChannelMutableStateFlow.asStateFlow()

    init {
        getLanguagesByChannel()
    }

    private fun getLanguagesByChannel(){
        languagesListByChannelMutableStateFlow.update {
            LanguagesData.getLanguagesChannel(context = context)
        }
    }
}