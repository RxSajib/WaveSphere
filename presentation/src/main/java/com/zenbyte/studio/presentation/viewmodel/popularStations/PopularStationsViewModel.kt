package com.zenbyte.studio.presentation.viewmodel.popularStations

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
import kotlin.collections.sortedBy
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class PopularStationsViewModel @Inject constructor(
    val localChannelUseCase: LocalChannelUseCase,
    val mediaPlayControllerUseCase: MediaPlayControllerUseCase
) : ViewModel(){

    var channelMutableStateFlow = MutableStateFlow<ApiState<List<MyChannel>>>(ApiState())

    val sortedChannels = channelMutableStateFlow.map { state ->
        state.copy(data = state.data?.sortedBy { it.name })
    }

    private val currentPayingChannelMutableStateFlow = MutableStateFlow<MyChannel?>(null)
    val currentPlayingChannel = currentPayingChannelMutableStateFlow.asStateFlow()
    var isMusicPlaying by mutableStateOf(false)

    init {
        getChannelFromLocal()
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

    private fun getChannelFromLocal(){
        viewModelScope.launch {
            channelMutableStateFlow.emit(ApiState( isSuccess = false, isLoading = true))

            localChannelUseCase.getLocalChannelList().collect { channelList ->
                channelMutableStateFlow.emit(ApiState(data = channelList, isSuccess = true, isLoading = false))
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