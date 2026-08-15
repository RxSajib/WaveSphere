package com.zenbyte.studio.presentation.viewmodel.getChannelByCountry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.usecase.GetChannelByCountryUseCase
import com.zenbyte.studio.domain.usecase.local.LocalChannelUseCase
import com.zenbyte.studio.domain.usecase.MediaPlayControllerUseCase
import com.zenbyte.studio.presentation.viewmodel.state.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "GetChannelByCountry"
@HiltViewModel
class GetChannelByCountryViewModel @Inject constructor(
    val getChannelByCountryUseCase: GetChannelByCountryUseCase,
    val mediaPlayControllerUseCase: MediaPlayControllerUseCase,
    val localChannelUseCase: LocalChannelUseCase
) : ViewModel() {


    private var channelListMutableStateFlow = MutableStateFlow<ApiState< List<MyChannel>>>(ApiState())
    val channelList = channelListMutableStateFlow.asStateFlow()

    var currentPlayingChannel = mediaPlayControllerUseCase.playerController.currentChannel






    fun getChannelByCountry(countryName: String) {
        viewModelScope.launch {
            localChannelUseCase.getChannelByCountry(countryName = countryName).collect {
                channelListMutableStateFlow.emit(ApiState(data = it, isSuccess = true, isLoading = false))
            }
        }
    }

}