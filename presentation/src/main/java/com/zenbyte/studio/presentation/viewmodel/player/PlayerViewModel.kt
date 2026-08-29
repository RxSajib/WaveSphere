package com.zenbyte.studio.presentation.viewmodel.player

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.AppLanguages
import com.zenbyte.studio.domain.usecase.AppSettingUseCase
import com.zenbyte.studio.domain.usecase.local.DataStoreUseCase
import com.zenbyte.studio.presentation.ui.data.AppConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

import dev.b3nedikt.app_locale.AppLocale
import java.util.Locale

private const val TAG = "PlayerViewModel"
@HiltViewModel
class PlayerViewModel @Inject constructor(
    val appSettingUseCase: AppSettingUseCase,
    val dataStoreUseCase: DataStoreUseCase
) : ViewModel() {

    var showLanguagesSheet by mutableStateOf(false)
    var selectedAppLanguages by mutableIntStateOf(0)

    fun onLanguageSelected(index: Int, code: String) {
        selectedAppLanguages = index
        AppLocale.desiredLocale = Locale.forLanguageTag(code)
    }

    val selectedLocale: String
        get() = AppLocale.currentLocale.language

    private val darkModeToggledMutableStateFlow = MutableStateFlow<Boolean>(false)
    val darkModeToggle = darkModeToggledMutableStateFlow.asStateFlow()


    private val appLanguagesMutableStateFlow = MutableStateFlow<List<AppLanguages>>(emptyList())
    val appLanguages = appLanguagesMutableStateFlow.asStateFlow()

    private val dataSaverToggledMutableStateFlow = MutableStateFlow<Boolean>(false)
    val dataSaverToggle = dataSaverToggledMutableStateFlow.asStateFlow()

    fun onDataSaverToggle(isDataSaver: Boolean) {
        viewModelScope.launch {
            dataSaverToggledMutableStateFlow.emit(isDataSaver)
        }
    }


    init {
        getAppLanguages()
        getThemeModeData()
    }

    private fun getThemeModeData(){
        viewModelScope.launch {
            dataStoreUseCase.getBoolData(key = AppConstant.ENABLE_DARK_MODE).collect{
                darkModeToggledMutableStateFlow.emit(it)
            }
        }
    }

    private fun getAppLanguages(){
        viewModelScope.launch {
            appSettingUseCase.getLanguages().collect{
                appLanguagesMutableStateFlow.emit(it)
            }
        }
    }

    fun onDarkModeToggle(key : String, value : Boolean){
        viewModelScope.launch {
            dataStoreUseCase.saveBooleanData(key = key, value = value)
        }
    }
}