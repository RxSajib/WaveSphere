package com.zenbyte.studio.presentation.viewmodel.getChannelByCountry

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.usecase.GetChannelByCountryUseCase
import com.zenbyte.studio.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "GetChannelByCountry"
@HiltViewModel
class GetChannelByCountryViewModel @Inject constructor(
    val getChannelByCountryUseCase: GetChannelByCountryUseCase
) : ViewModel() {

    private var channelListMutableStateFlow = MutableStateFlow<List<MyChannel>>(emptyList())
    val channelList = channelListMutableStateFlow.asStateFlow()

    init {
        getChannelByCountry(countryName = "Bangladesh")
    }

    fun getChannelByCountry(countryName: String) {
        viewModelScope.launch {
            when(val response = getChannelByCountryUseCase.getChannelByCountry(countryName)){
               is Resource.Success -> {
                   channelListMutableStateFlow.emit(response.data?: emptyList())
               }
               is Resource.Error -> {
                   channelListMutableStateFlow.emit(emptyList())
               }
               is Resource.Loading -> {

               }
           }
        }
    }
}