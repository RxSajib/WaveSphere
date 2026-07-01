package com.zenbyte.studio.presentation.viewmodel.getChannelByCountry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.usecase.GetChannelByCountryUseCase
import com.zenbyte.studio.domain.usecase.MediaPlayControllerUseCase
import com.zenbyte.studio.domain.utils.Resource
import com.zenbyte.studio.presentation.viewmodel.state.ApiState
import com.zenbyte.studio.presentation.viewmodel.utils.MyCustomLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val TAG = "GetChannelByCountry"
@HiltViewModel
class GetChannelByCountryViewModel @Inject constructor(
    val getChannelByCountryUseCase: GetChannelByCountryUseCase,
    val mediaPlayControllerUseCase: MediaPlayControllerUseCase
) : ViewModel() {


    private var channelListMutableStateFlow = MutableStateFlow<ApiState< List<MyChannel>>>(ApiState())
    val channelList = channelListMutableStateFlow.asStateFlow()

    var currentPlayingChannel = mediaPlayControllerUseCase.playerController.currentChannel





    fun getChannelByCountry(countryName: String) {
        viewModelScope.launch {
            channelListMutableStateFlow.emit(ApiState( isLoading = true))
            when(val response = getChannelByCountryUseCase.getChannelByCountry(countryName)){
               is Resource.Success -> {
                   channelListMutableStateFlow.emit(ApiState(data = response.data, isSuccess = true, isLoading = false))
               }
               is Resource.Error -> {
                   channelListMutableStateFlow.emit(ApiState(errorMessage = response.message, isSuccess = false, isLoading = false))
                   channelListMutableStateFlow.emit(ApiState(data = emptyList()))
               }
               is Resource.Loading -> {
                   channelListMutableStateFlow.emit(ApiState( isLoading = true))
               }
           }
        }
    }

}