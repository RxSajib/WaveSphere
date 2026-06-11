package com.zenbyte.studio.presentation.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.usecase.GetChannelByCountryUseCase
import com.zenbyte.studio.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getChannelByCountryUseCase: GetChannelByCountryUseCase
) : ViewModel() {

    private var channelMutableStateFlow = MutableStateFlow<List<MyChannel>>(emptyList())
    val channelList = channelMutableStateFlow.asStateFlow()

    val tranChannel = channelMutableStateFlow.asStateFlow().map {
       it.sortedByDescending { it.votes }.take(8)
    }
    val popularStation = channelMutableStateFlow.asStateFlow().map {
        it.sortedBy { it.name }.take(5)
    }

    init {
        getChannelByCountry()
    }

    private fun getChannelByCountry(){
        viewModelScope.launch {
            val response = getChannelByCountryUseCase.getChannelByCountry("Bangladesh")
            when(response){
                is Resource.Success -> {
                    channelMutableStateFlow.emit(response.data?: emptyList())
                }
                is Resource.Error -> {
                    channelMutableStateFlow.emit(emptyList())
                }
                is Resource.Loading -> {

                }
            }
        }
    }
}