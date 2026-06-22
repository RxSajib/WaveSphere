package com.zenbyte.studio.presentation.viewmodel.playerView

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.usecase.GetSingleSaveChannel
import com.zenbyte.studio.domain.usecase.IsChannelSavedUseCase
import com.zenbyte.studio.domain.usecase.RemoveSaveChannelUseCase
import com.zenbyte.studio.domain.usecase.SaveChannelUseCase
import com.zenbyte.studio.presentation.viewmodel.utils.MyCustomLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "PlayerViewModel"

@HiltViewModel
class PlayerViewModel @Inject constructor(
    val saveChannelUseCase: SaveChannelUseCase,
    val getSingleSaveChannel: GetSingleSaveChannel,
    val isChannelSavedUseCase: IsChannelSavedUseCase,
    val removeSaveChannelUseCase: RemoveSaveChannelUseCase
) : ViewModel() {

    private val _volume = MutableStateFlow(50f)
    val volume = _volume.asStateFlow()

    fun updateVolume(value: Float) {
        _volume.value = value
    }

    private val _playingUID = MutableStateFlow<String>("")
    val playingUID = _playingUID.asStateFlow()

    fun updatePlayingUID(value: String) {
        viewModelScope.launch {
            _playingUID.emit(value)
        }
    }


    private val _channelUID = MutableStateFlow<String>("")
    val channelUID = _channelUID.asStateFlow()

    fun updateChannelUID(value: String) {
        MyCustomLogger.logMessageInfo(tag = TAG, message = "channel id $value")
        viewModelScope.launch {
            _channelUID.emit(value)
        }
    }

    val isButtonEnable: Flow<Boolean> = combine(playingUID, channelUID) { playingUID, channelUID ->
        Log.d(TAG, "updateChannelUID: $channelUID")
        Log.d(TAG, "updatePlayingUID: $playingUID")
        Log.d(TAG, "value: ${playingUID == channelUID}")
        playingUID == channelUID

    }



    @OptIn(ExperimentalCoroutinesApi::class)
    val isChannelSaved = playingUID.flatMapLatest{id ->
        isChannelSavedUseCase.isChannelSaved(stationuuid = id)
    }


    fun saveChannel(myChannel: MyChannel){
        viewModelScope.launch {

                if(isChannelSaved.first()){
                    removeSaveChannelUseCase.removeSaveChannel(channelID = myChannel.stationuuid)
                }else {
                    saveChannelUseCase.saveChannel(myChannel = myChannel)
                }
        }
    }


}