package com.zenbyte.studio.presentation.viewmodel.playerView

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor() : ViewModel() {

    private val _volume = MutableStateFlow(50f)
    val volume = _volume.asStateFlow()

    fun updateVolume(value: Float) {
        _volume.value = value
    }
}