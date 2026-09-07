package com.zenbyte.studio.presentation.viewmodel.playbackSetting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.usecase.local.DataStoreUseCase
import com.zenbyte.studio.presentation.ui.data.AppConstant.ENABLE_AUTO_RECONNECT
import com.zenbyte.studio.presentation.ui.data.AppConstant.ENABLE_CONTINUE_IN_BACKGROUND
import com.zenbyte.studio.presentation.ui.data.AppConstant.ENABLE_CROSS_FADE
import com.zenbyte.studio.presentation.ui.data.AppConstant.ENABLE_SHOW_LIVE_INDICATOR
import com.zenbyte.studio.presentation.ui.data.AppConstant.SEEKER_VALUE
import com.zenbyte.studio.presentation.ui.data.AppConstant.SELECTED_AUDIO_QUALITY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaybackSettingViewModel @Inject constructor(
    val dataStoreUseCase: DataStoreUseCase
) : ViewModel() {

    val isAutoReconnect = dataStoreUseCase.getBoolData(key = ENABLE_AUTO_RECONNECT)
    val isContinueInBackground = dataStoreUseCase.getBoolData(key = ENABLE_CONTINUE_IN_BACKGROUND)
    val isLiveIndicator = dataStoreUseCase.getBoolData(key = ENABLE_SHOW_LIVE_INDICATOR)
    val isCrossFade = dataStoreUseCase.getBoolData(key = ENABLE_CROSS_FADE)
    val seekerValue = dataStoreUseCase.getFlotData(key = SEEKER_VALUE)
    val selectedAudioQuality = dataStoreUseCase.getStringData(key = SELECTED_AUDIO_QUALITY)

    fun saveSetting(key : String, value : Boolean){
        viewModelScope.launch {
            dataStoreUseCase.saveBooleanData(key = key, value = value)
        }
    }
    fun saveSeekerValue(key : String, value : Float){
        viewModelScope.launch {
            dataStoreUseCase.saveFlotData(key = key, value = value)
        }
    }

    fun saveAudioQuality(value : String?){
        viewModelScope.launch {
            dataStoreUseCase.saveStringData(key = SELECTED_AUDIO_QUALITY, value = value ?: "DEFAULT")
        }
    }
}