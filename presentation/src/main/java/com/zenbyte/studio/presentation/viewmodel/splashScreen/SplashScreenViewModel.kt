package com.zenbyte.studio.presentation.viewmodel.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class SplashScreenViewModel @Inject constructor() : ViewModel() {

    var navigateToHome = MutableSharedFlow<Boolean>()

    init {
        viewModelScope.launch {
            delay(4.seconds)
            navigateToHome.emit(true)
        }
    }
}