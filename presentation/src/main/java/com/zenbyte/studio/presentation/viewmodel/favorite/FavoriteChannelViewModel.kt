package com.zenbyte.studio.presentation.viewmodel.favorite

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.usecase.CountryListUseCase
import com.zenbyte.studio.domain.usecase.GetAllFavoriteChannelUseCase
import com.zenbyte.studio.domain.usecase.GetSingleSaveChannel
import com.zenbyte.studio.domain.usecase.IsChannelSavedUseCase
import com.zenbyte.studio.domain.usecase.MediaPlayControllerUseCase
import com.zenbyte.studio.domain.usecase.RemoveSaveChannelUseCase
import com.zenbyte.studio.domain.usecase.SaveChannelUseCase
import com.zenbyte.studio.domain.usecase.local.LocalChannelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class FavoriteChannelViewModel @Inject constructor(
    val getAllFavoriteChannelUseCase: GetAllFavoriteChannelUseCase,
    val getSingleSaveChannel: GetSingleSaveChannel,
    val saveChannelUseCase: SaveChannelUseCase,
    val removeSaveChannelUseCase: RemoveSaveChannelUseCase,
    val mediaPlayControllerUseCase: MediaPlayControllerUseCase,
    val localChannelUseCase: LocalChannelUseCase,
    val countryListUseCase: CountryListUseCase,
    val isChannelSavedUseCase: IsChannelSavedUseCase
) : ViewModel() {

    val favoriteChannel = getAllFavoriteChannelUseCase.getFavoriteChannel()

    private val currentPayingChannelMutableStateFlow = MutableStateFlow<MyChannel?>(null)
    val currentPlayingChannel = currentPayingChannelMutableStateFlow.asStateFlow()
    var isMusicPlaying by mutableStateOf(false)

    private var channelIdMutableStateFlow = MutableStateFlow("")
    val channelId = channelIdMutableStateFlow.asStateFlow()


    init {
        isMusicPlaying()
        getCurrentPlayingChannelInfo()
    }

    private fun getCurrentPlayingChannelInfo() {
        viewModelScope.launch {
            mediaPlayControllerUseCase.playerController.currentChannel.collect { myChannel ->
                currentPayingChannelMutableStateFlow.emit(myChannel)
            }
        }

    }

    private fun isMusicPlaying() {
        viewModelScope.launch {
            mediaPlayControllerUseCase.playerController.isPlaying.collect { isPlaying ->
                isMusicPlaying = isPlaying
            }
        }
    }
    fun saveChannelID(channelID : String){
        viewModelScope.launch {
            channelIdMutableStateFlow.emit(channelID)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val channel = channelId.flatMapLatest { channelID ->
        getSingleSaveChannel.getSingleChannel(channelID = channelID)
    }

    fun saveChannel(myChannel: MyChannel){
        viewModelScope.launch {
            saveChannelUseCase.saveChannel(myChannel = myChannel)
        }
    }

    fun removeChannel(channelID : String){
        viewModelScope.launch {
            removeSaveChannelUseCase.removeSaveChannel(channelID = channelID)
        }
    }

    fun mediaPlayController(myChannel: MyChannel, channels: List<MyChannel>, index: Int) {
        viewModelScope.launch {
            mediaPlayControllerUseCase.playerController.isLoading.first().let {
                if (myChannel.stationuuid == currentPlayingChannel.value?.stationuuid) {
                    mediaPlayControllerUseCase.playerController.pause()
                } else { mediaPlayControllerUseCase.playAudio(myChannel = channels, index = index)

                }
            }
        }

    }

    fun isPlaying(myChannel: MyChannel) : StateFlow<Boolean>{
        return flow {
            mediaPlayControllerUseCase.playerController.isPlaying.collect { isPlaying ->
                if(myChannel.stationuuid == currentPlayingChannel.value?.stationuuid){
                    emit(isPlaying)
                    return@collect
                }else{
                    emit(false)
                    return@collect
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(500.milliseconds),
            initialValue = false
        )
    }

    fun isBufferingChannel(myChannel: MyChannel): StateFlow<Boolean> {

        return flow {
            mediaPlayControllerUseCase.playerController.isLoading.collect { isPlaying ->
                if (myChannel.stationuuid == currentPlayingChannel.value?.stationuuid) {
                    emit(isPlaying)
                    return@collect
                } else {
                    emit(false)
                    return@collect
                }
            }

        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(500.milliseconds),
            initialValue = false
        )
    }
}