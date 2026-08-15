package com.zenbyte.studio.presentation.viewmodel.premium

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PremiumViewModel @Inject constructor() : ViewModel() {

    val selectedItemMutableStateFlow = MutableStateFlow<Int>(1)
    val selectedItem = selectedItemMutableStateFlow

    fun onSelectedItemChanged(index: Int) {
        viewModelScope.launch {
            selectedItemMutableStateFlow.emit(index)
        }
    }
}