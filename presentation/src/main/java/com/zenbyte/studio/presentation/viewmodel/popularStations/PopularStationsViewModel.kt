package com.zenbyte.studio.presentation.viewmodel.popularStations

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
import kotlin.collections.sortedBy

@HiltViewModel
class PopularStationsViewModel @Inject constructor(
    val localChannelUseCase: LocalChannelUseCase
) : ViewModel(){

    var channelMutableStateFlow = MutableStateFlow<ApiState<List<MyChannel>>>(ApiState())

    val sortedChannels = channelMutableStateFlow.map { state ->
        state.copy(data = state.data?.sortedBy { it.name })
    }

    init {
        getChannelFromLocal()
    }

    private fun getChannelFromLocal(){
        viewModelScope.launch {
            channelMutableStateFlow.emit(ApiState( isSuccess = false, isLoading = true))

            localChannelUseCase.getLocalChannelList().collect { channelList ->
                channelMutableStateFlow.emit(ApiState(data = channelList, isSuccess = true, isLoading = false))
            }
        }
    }
}