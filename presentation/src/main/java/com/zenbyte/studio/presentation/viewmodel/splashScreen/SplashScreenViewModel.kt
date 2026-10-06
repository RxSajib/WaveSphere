package com.zenbyte.studio.presentation.viewmodel.splashScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

private const val TAG = "SplashScreenViewModel"

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
) : ViewModel() {

    private var channelMutableStateFlow = MutableStateFlow<List<MyChannel>>(emptyList())
    val channelList = channelMutableStateFlow.asStateFlow()
    private val isLoadChannelMutableStateFlow = MutableStateFlow(false)
    val isLoadChannel = isLoadChannelMutableStateFlow.asStateFlow()
    var splashScreenState by mutableStateOf(false)

    init {
        viewModelScope.launch {
            delay(3000.milliseconds)
            splashScreenState = true
        }
    }


}