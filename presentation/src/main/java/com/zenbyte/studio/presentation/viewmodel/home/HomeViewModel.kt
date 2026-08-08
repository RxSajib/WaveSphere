package com.zenbyte.studio.presentation.viewmodel.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.usecase.CountryListUseCase
import com.zenbyte.studio.domain.usecase.GetAllRadioStationsUseCase
import com.zenbyte.studio.domain.usecase.GetChannelByCountryUseCase
import com.zenbyte.studio.domain.usecase.local.LocalChannelUseCase
import com.zenbyte.studio.domain.utils.Resource
import com.zenbyte.studio.presentation.viewmodel.utils.Extras
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeViewModel"
@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext val myContext: Context,
    val getChannelByCountryUseCase: GetChannelByCountryUseCase,
    val getAllRadioStationsUseCase: GetAllRadioStationsUseCase,
    val localChannelUseCase: LocalChannelUseCase,
    val countryListUseCase: CountryListUseCase,
) : ViewModel() {


    private var channelMutableStateFlow = MutableStateFlow<List<MyChannel>>(emptyList())
    val channelList = channelMutableStateFlow.asStateFlow()

    val tranChannel = channelMutableStateFlow.asStateFlow().map {
       it.sortedByDescending { it.votes }.take(8)
    }
    val popularStation = channelMutableStateFlow.asStateFlow().map { it ->
        it.sortedBy { it.name }.take(5)
    }

    init {
       // getChannelByCountry()
   //     getAllChannel()
            //   getAllLocalChannel()
        getChannelByCountryCode()
    }

    private fun getChannelByCountryCode(){
        viewModelScope.launch {
            localChannelUseCase.getChannelByCountryCode(Extras.getSimCountry(context = myContext).ifEmpty{"USA"}).collect{channelList ->
                channelMutableStateFlow.emit(channelList)
            }
        }
    }
    
    private fun getAllLocalChannel(){
       viewModelScope.launch {
           when(countryListUseCase.getCountryList()){
               is Resource.Success -> {

               }
               is Resource.Error -> {

               }
               is Resource.Loading -> {

               }
           }
       }
    }

    private fun getAllChannel(){
        viewModelScope.launch {
            val allchannelResponse = countryListUseCase.getCountryList()
            when(allchannelResponse){
                is Resource.Success -> {
                    for (channel in allchannelResponse.data?: emptyList()){
                        Log.d(TAG, "getAllChannel: ${channel.name}")
                        getChannelByCountryUseCase.getChannelByCountry(countryName = channel.name)
                    }
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        }
    }

    private fun getChannelByCountry(){
        viewModelScope.launch {

            val response = getChannelByCountryUseCase.getChannelByCountry(Extras.getSimCountry(context = myContext).ifEmpty { "IR" })
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