package com.zenbyte.studio.presentation.viewmodel.getChannelByCountry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.model.UiState
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
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

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

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

    fun getChannelByCountryRemote(countryName : String) {
        viewModelScope.launch {
            val response = getChannelByCountryUseCase.getChannelByCountry(countryName = countryName)
            response.collect { response ->
                when (response) {
                    is com.zenbyte.studio.domain.utils.Resource.Success -> {}
                    is com.zenbyte.studio.domain.utils.Resource.Loading -> {
                        if (localChannelUseCase.getChannelByCountry(countryName = countryName)
                                .first().isEmpty()
                        ) {
                            _uiState.update {
                                UiState(
                                    isInitialLoading = true
                                )
                            }
                        }
                    }

                    is com.zenbyte.studio.domain.utils.Resource.Error -> {
                        if (localChannelUseCase.getChannelByCountry(countryName = countryName)
                                .first().isEmpty()
                        ) {
                            _uiState.emit(
                                UiState(
                                    error = response.message
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    fun getChannelByCountry(countryName: String) {
        viewModelScope.launch {
            localChannelUseCase.getChannelByCountry(countryName = countryName).collect { channels ->
                _uiState.update {
                    it.copy(
                        data = channels,
                        isInitialLoading =
                            channels.isEmpty() && it.isInitialLoading
                    )
                }
            }
        }
    }

}