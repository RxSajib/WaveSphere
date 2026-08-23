package com.zenbyte.studio.presentation.viewmodel.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.usecase.local.DataStoreUseCase
import com.zenbyte.studio.presentation.ui.data.AppConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class RootViewModel @Inject constructor(
    val dataStoreUseCase: DataStoreUseCase
) : ViewModel() {

  //  private val darkModeToggledMutableStateFlow = MutableStateFlow<Boolean>(false)
   // val darkModeToggle = darkModeToggledMutableStateFlow.asStateFlow()

    init {
        getThemeModeData()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val darkModeToggle = dataStoreUseCase.getBoolData(key = AppConstant.ENABLE_DARK_MODE).flatMapLatest {darkMode ->
        flow {
            emit(darkMode)
        }
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = false
    )

    private fun getThemeModeData(){
        viewModelScope.launch {
            dataStoreUseCase.getBoolData(key = AppConstant.ENABLE_DARK_MODE).collect{

            }
        }
    }
}