package com.zenbyte.studio.presentation.viewmodel.playerView

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.usecase.GetSingleSaveChannel
import com.zenbyte.studio.domain.usecase.IsChannelSavedUseCase
import com.zenbyte.studio.domain.usecase.MediaPlayControllerUseCase
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
    val removeSaveChannelUseCase: RemoveSaveChannelUseCase,
    val mediaPlayControllerUseCase: MediaPlayControllerUseCase,
) : ViewModel() {


    val currentChannel = mediaPlayControllerUseCase.playerController.currentChannel
    val isPlaying = mediaPlayControllerUseCase.playerController.isPlaying
    val isLoading = mediaPlayControllerUseCase.playerController.isLoading

    fun playAudio(myChannel: List<MyChannel>, index: Int) {
        mediaPlayControllerUseCase.playAudio(myChannel, index)
    }

    fun pauseAudio() {
        mediaPlayControllerUseCase.playerController.pause()
    }

    fun nextPlayBack() {
        mediaPlayControllerUseCase.playerController.next()
    }

    fun previousPlayBack() {
        mediaPlayControllerUseCase.playerController.previous()
    }


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

    private val _selectedChannelMutableStateFlow = MutableStateFlow<MyChannel>(MyChannel())
    val selectedChannel = _selectedChannelMutableStateFlow.asStateFlow()

    fun updateChannelUID(value: String) {
        MyCustomLogger.logMessageInfo(tag = TAG, message = "channel id $value")
        viewModelScope.launch {
            _channelUID.emit(value)
        }
    }

    fun selectedChannel(selectedChannel: MyChannel){
        viewModelScope.launch {
            _selectedChannelMutableStateFlow.emit(selectedChannel)
        }
    }

    val isPlayingChannel: Flow<Boolean> = combine(
        selectedChannel,
        currentChannel,
        playingUID,
        channelUID,
        mediaPlayControllerUseCase.playerController.isPlaying
    ) {selectedChannel, currentChannel, playingUID, channelUID, isPlayingMyAudio ->
        Log.d(TAG, "currentChannel: $currentChannel")
        Log.d(TAG, "updateChannelUID: $channelUID")
        Log.d(TAG, "updatePlayingUID: $playingUID")
        Log.d(TAG, "selectedChannel: ${selectedChannel.stationuuid}")
     //   Log.d(TAG, "value: ${playingUID == channelUID}")
            if(selectedChannel.stationuuid == channelUID){
                Log.d(TAG, "same value: ${playingUID == channelUID}")
                if(isPlayingMyAudio){
                    true
                }else {
                    false
                }

            }else {
                false
            }
    }


    val isPlayingAudio: Flow<Boolean> =
        combine(mediaPlayControllerUseCase.playerController.isPlaying) { isPlayingMyAudio ->
            true
        }


    @OptIn(ExperimentalCoroutinesApi::class)
    val isChannelSaved = playingUID.flatMapLatest { id ->
        isChannelSavedUseCase.isChannelSaved(stationuuid = id)
    }


    fun saveChannel(myChannel: MyChannel) {
        viewModelScope.launch {

            if (isChannelSaved.first()) {
                removeSaveChannelUseCase.removeSaveChannel(channelID = myChannel.stationuuid)
            } else {
                saveChannelUseCase.saveChannel(myChannel = myChannel)
            }
        }
    }


}