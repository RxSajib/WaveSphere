package com.zenbyte.studio.presentation.viewmodel.playerView

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.usecase.SaveChannelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "PlayerViewModel"

@HiltViewModel
class PlayerViewModel @Inject constructor(
    val saveChannelUseCase: SaveChannelUseCase
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

    fun saveChannel(myChannel: MyChannel){
        viewModelScope.launch {
            saveChannelUseCase.saveChannel(myChannel = myChannel)
        }
    }

}