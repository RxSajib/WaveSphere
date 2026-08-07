package com.zenbyte.studio.presentation.viewmodel.trendingStations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.usecase.LocalChannelUseCase
import com.zenbyte.studio.presentation.viewmodel.state.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingStationsViewModel @Inject constructor(
    val localChannelUseCase: LocalChannelUseCase
) : ViewModel() {

    private var channelMutableStateFlow = MutableStateFlow< ApiState<List<MyChannel>>>(ApiState())
    val trendingChannelList  = channelMutableStateFlow.map { state ->
        state.copy(data = state.data?.sortedBy { it.votes })
    }

    init {
        getChannelsFromLocal()
    }

    private fun getChannelsFromLocal(){
        viewModelScope.launch {
            channelMutableStateFlow.emit(ApiState( isSuccess = false, isLoading = true))
            localChannelUseCase.getLocalChannelList().collect { listOfChannel ->
                channelMutableStateFlow.emit(ApiState(data = listOfChannel, isSuccess = true, isLoading = false))
            }
        }
    }
}