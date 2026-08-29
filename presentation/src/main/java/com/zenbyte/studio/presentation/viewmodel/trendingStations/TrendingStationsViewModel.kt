package com.zenbyte.studio.presentation.viewmodel.trendingStations

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.usecase.MediaPlayControllerUseCase
import com.zenbyte.studio.domain.usecase.local.LocalChannelUseCase
import com.zenbyte.studio.presentation.viewmodel.state.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class TrendingStationsViewModel @Inject constructor(
    val localChannelUseCase: LocalChannelUseCase,
    val mediaPlayControllerUseCase: MediaPlayControllerUseCase
) : ViewModel() {

    private var channelMutableStateFlow = MutableStateFlow< ApiState<List<MyChannel>>>(ApiState())
    val trendingChannelList  = channelMutableStateFlow.map { state ->
        state.copy(data = state.data?.sortedBy { it.votes })
    }

    private val currentPayingChannelMutableStateFlow = MutableStateFlow<MyChannel?>(null)
    val currentPlayingChannel = currentPayingChannelMutableStateFlow.asStateFlow()
    var isMusicPlaying by mutableStateOf(false)

    init {
        getChannelsFromLocal()
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

    private fun getChannelsFromLocal(){
        viewModelScope.launch {
            channelMutableStateFlow.emit(ApiState( isSuccess = false, isLoading = true))
            localChannelUseCase.getLocalChannelList().collect { listOfChannel ->
                channelMutableStateFlow.emit(ApiState(data = listOfChannel, isSuccess = true, isLoading = false))
            }
        }
    }


    fun isPlaying(myChannel: MyChannel): StateFlow<Boolean> {
        return flow {
            mediaPlayControllerUseCase.playerController.isPlaying.collect { isPlaying ->
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