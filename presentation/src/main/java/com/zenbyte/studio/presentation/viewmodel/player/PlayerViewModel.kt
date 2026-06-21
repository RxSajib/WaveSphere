package com.zenbyte.studio.presentation.viewmodel.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.usecase.SaveChannelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
) : ViewModel() {

    private val darkModeToggledMutableStateFlow = MutableStateFlow<Boolean>(false)
    val darkModeToggle = darkModeToggledMutableStateFlow.asStateFlow()

    fun onDarkModeToggle(isDarkMode: Boolean) {
        viewModelScope.launch {
            darkModeToggledMutableStateFlow.emit(isDarkMode)
        }
    }


    private val dataSaverToggledMutableStateFlow = MutableStateFlow<Boolean>(false)
    val dataSaverToggle = dataSaverToggledMutableStateFlow.asStateFlow()

    fun onDataSaverToggle(isDataSaver: Boolean) {
        viewModelScope.launch {
            dataSaverToggledMutableStateFlow.emit(isDataSaver)
        }
    }



}