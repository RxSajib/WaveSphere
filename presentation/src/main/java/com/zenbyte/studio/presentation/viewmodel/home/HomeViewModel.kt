package com.zenbyte.studio.presentation.viewmodel.home

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.usecase.CountryListUseCase
import com.zenbyte.studio.domain.usecase.GetAllFavoriteChannelUseCase
import com.zenbyte.studio.domain.usecase.GetAllRadioStationsUseCase
import com.zenbyte.studio.domain.usecase.GetChannelByCountryUseCase
import com.zenbyte.studio.domain.usecase.GetSingleSaveChannel
import com.zenbyte.studio.domain.usecase.IsChannelSavedUseCase
import com.zenbyte.studio.domain.usecase.MediaPlayControllerUseCase
import com.zenbyte.studio.domain.usecase.RemoveSaveChannelUseCase
import com.zenbyte.studio.domain.usecase.SaveChannelUseCase
import com.zenbyte.studio.domain.usecase.local.LocalChannelUseCase
import com.zenbyte.studio.domain.utils.Resource
import com.zenbyte.studio.presentation.viewmodel.utils.Extras
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
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
    val mediaPlayControllerUseCase: MediaPlayControllerUseCase,
    val getAllFavoriteChannelUseCase: GetAllFavoriteChannelUseCase,
    val getSingleSaveChannel: GetSingleSaveChannel,
    val saveChannelUseCase: SaveChannelUseCase,
    val removeSaveChannelUseCase: RemoveSaveChannelUseCase,
    val isChannelSavedUseCase: IsChannelSavedUseCase
) : ViewModel() {


    private var channelMutableStateFlow = MutableStateFlow<List<MyChannel>>(emptyList())
    val channelList = channelMutableStateFlow.asStateFlow()

    val tranChannel = channelMutableStateFlow.asStateFlow().map {
        it.sortedByDescending { it.votes }.take(8)
    }
    val popularStation = channelMutableStateFlow.asStateFlow().map { it ->
        it.sortedBy { it.name }.take(5)
    }

    private val currentPayingChannelMutableStateFlow = MutableStateFlow<MyChannel?>(null)
    val currentPlayingChannel = currentPayingChannelMutableStateFlow.asStateFlow()
    var isMusicPlaying by mutableStateOf(false)



    init {
        // getChannelByCountry()
        //     getAllChannel()
        //   getAllLocalChannel()
        getChannelByCountryCode()
        getCurrentPlayingChannelInfo()
        isMusicPlaying()
    }

    fun saveChannel(myChannel: MyChannel){
        viewModelScope.launch {
            if(!isChannelSavedUseCase.isChannelSaved(myChannel.stationuuid).first()){
                saveChannelUseCase.saveChannel(myChannel = myChannel)
            }else {
                removeSaveChannelUseCase.removeSaveChannel(channelID = myChannel.stationuuid)
            }

        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getSaveChannel(channelID: String) =
        isChannelSavedUseCase.isChannelSaved(stationuuid = channelID)




    fun playPushController() {
        if (isMusicPlaying) {
            mediaPlayControllerUseCase.playerController.pause()
        } else {
            mediaPlayControllerUseCase.playerController.singlePlay()
        }
    }

    private fun isMusicPlaying() {
        viewModelScope.launch {
            mediaPlayControllerUseCase.playerController.isPlaying.collect { isPlaying ->
                isMusicPlaying = isPlaying
            }
        }
    }

    private fun getCurrentPlayingChannelInfo() {
        viewModelScope.launch {
            mediaPlayControllerUseCase.playerController.currentChannel.collect { myChannel ->
                currentPayingChannelMutableStateFlow.emit(myChannel)
            }
        }

    }

    private fun getChannelByCountryCode() {
        viewModelScope.launch {
            localChannelUseCase.getChannelByCountryCode(
                Extras.getSimCountry(context = myContext).ifEmpty { "USA" })
                .collect { channelList ->
                    channelMutableStateFlow.emit(channelList)
                }
        }
    }

    private fun getAllLocalChannel() {
        viewModelScope.launch {
            when (countryListUseCase.getCountryList()) {
                is Resource.Success -> {

                }

                is Resource.Error -> {

                }

                is Resource.Loading -> {

                }
            }
        }
    }

    private fun getAllChannel() {
        viewModelScope.launch {
            val allchannelResponse = countryListUseCase.getCountryList()
            when (allchannelResponse) {
                is Resource.Success -> {
                    for (channel in allchannelResponse.data ?: emptyList()) {
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

    private fun getChannelByCountry() {
        viewModelScope.launch {

            val response = getChannelByCountryUseCase.getChannelByCountry(
                Extras.getSimCountry(context = myContext).ifEmpty { "IR" })
            when (response) {
                is Resource.Success -> {
                    channelMutableStateFlow.emit(response.data ?: emptyList())
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