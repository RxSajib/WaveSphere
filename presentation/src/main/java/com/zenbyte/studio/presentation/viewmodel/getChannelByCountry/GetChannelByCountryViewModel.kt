package com.zenbyte.studio.presentation.viewmodel.getChannelByCountry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.usecase.GetChannelByCountryUseCase
import com.zenbyte.studio.domain.usecase.local.LocalChannelUseCase
import com.zenbyte.studio.domain.usecase.MediaPlayControllerUseCase
import com.zenbyte.studio.presentation.viewmodel.state.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

private const val TAG = "GetChannelByCountry"
@HiltViewModel
class GetChannelByCountryViewModel @Inject constructor(
    val getChannelByCountryUseCase: GetChannelByCountryUseCase,
    val mediaPlayControllerUseCase: MediaPlayControllerUseCase,
    val localChannelUseCase: LocalChannelUseCase
) : ViewModel() {


    private var channelListMutableStateFlow = MutableStateFlow<ApiState< List<MyChannel>>>(ApiState(isLoading = true))
    val channelList = channelListMutableStateFlow.asStateFlow()

    private val currentPayingChannelMutableStateFlow = MutableStateFlow<MyChannel?>(null)
    val currentPlayingChannel = currentPayingChannelMutableStateFlow.asStateFlow()
    var isMusicPlaying by mutableStateOf(false)

    init {
        getCurrentPlayingChannelInfo()
    }
    private fun getCurrentPlayingChannelInfo() {
        viewModelScope.launch {
            mediaPlayControllerUseCase.playerController.currentChannel.collect { myChannel ->
                currentPayingChannelMutableStateFlow.emit(myChannel)
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



    fun getChannelByCountry(countryName: String) {
        viewModelScope.launch {
            localChannelUseCase.getChannelByCountry(countryName = countryName).collect {
                channelListMutableStateFlow.emit(ApiState(data = it, isSuccess = true, isLoading = false))
            }
        }
    }

}