package com.zenbyte.studio.presentation.viewmodel.home

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextDecoration.Companion.combine
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.model.UiState
import com.zenbyte.studio.domain.usecase.CountryListUseCase
import com.zenbyte.studio.domain.usecase.GetAllFavoriteChannelUseCase
import com.zenbyte.studio.domain.usecase.GetAllRadioStationsUseCase
import com.zenbyte.studio.domain.usecase.GetChannelByCountryUseCase
import com.zenbyte.studio.domain.usecase.GetSingleSaveChannel
import com.zenbyte.studio.domain.usecase.IsChannelSavedUseCase
import com.zenbyte.studio.domain.usecase.MediaPlayControllerUseCase
import com.zenbyte.studio.domain.usecase.RemoveSaveChannelUseCase
import com.zenbyte.studio.domain.usecase.SaveChannelUseCase
import com.zenbyte.studio.domain.usecase.SearchChannelUseCase
import com.zenbyte.studio.domain.usecase.local.DataStoreUseCase
import com.zenbyte.studio.domain.usecase.local.LocalChannelUseCase
import com.zenbyte.studio.domain.utils.Resource
import com.zenbyte.studio.presentation.ui.data.AppConstant.ENABLE_SHOW_LIVE_INDICATOR
import com.zenbyte.studio.presentation.viewmodel.utils.Extras.getSimCountry
import com.zenbyte.studio.presentation.viewmodel.utils.MyCustomLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.microseconds
import kotlin.time.Duration.Companion.milliseconds

private const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext val myContext: Context,
    val getChannelByCountryUseCase: GetChannelByCountryUseCase,
    val getAllRadioStationsUseCase: GetAllRadioStationsUseCase,
    val searchChannelUseCase: SearchChannelUseCase,
    val localChannelUseCase: LocalChannelUseCase,
    val countryListUseCase: CountryListUseCase,
    val mediaPlayControllerUseCase: MediaPlayControllerUseCase,
    val getAllFavoriteChannelUseCase: GetAllFavoriteChannelUseCase,
    val getSingleSaveChannel: GetSingleSaveChannel,
    val saveChannelUseCase: SaveChannelUseCase,
    val removeSaveChannelUseCase: RemoveSaveChannelUseCase,
    val isChannelSavedUseCase: IsChannelSavedUseCase,
    val dataStoreUseCase: DataStoreUseCase
) : ViewModel() {


    private var channelMutableStateFlow = MutableStateFlow<List<MyChannel>>(emptyList())
    val channelList = channelMutableStateFlow.asStateFlow()

    val tranChannel = channelMutableStateFlow.asStateFlow().map {
        it.sortedByDescending { it.votes }.take(8)
    }
    val popularStation = channelMutableStateFlow.asStateFlow().map { it ->
        it.sortedBy { it.name }.take(5)
    }

    val isShowLiveIndicator = dataStoreUseCase.getBoolData(key = ENABLE_SHOW_LIVE_INDICATOR)

    private val currentPayingChannelMutableStateFlow = MutableStateFlow<MyChannel?>(null)
    val currentPlayingChannel = currentPayingChannelMutableStateFlow.asStateFlow()
    var isMusicPlaying by mutableStateOf(false)

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
         getChannelByCountry()
        //     getAllChannel()
        //   getAllLocalChannel()
        getChannelByCountryCode()
        getCurrentPlayingChannelInfo()
        isMusicPlaying()
        getPopularTrendingStations()
    }

    private fun getPopularTrendingStations(){
        viewModelScope.launch {
            localChannelUseCase.getChannelByCountryCode(countryCode = myContext.getSimCountry().ifEmpty { "USA" }).collect { channels ->
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

    fun mediaPlayController(myChannel: MyChannel, channels: List<MyChannel>, index: Int) {
        viewModelScope.launch {
            mediaPlayControllerUseCase.playerController.isPlaying.first().let { isPlaying ->
                if (isPlaying) {
                    if(currentPlayingChannel.value == null){
                        mediaPlayControllerUseCase.playAudio( myChannel = channels, index = index)
                    }else {
                        if(myChannel.stationuuid == currentPlayingChannel.value?.stationuuid){
                            mediaPlayControllerUseCase.playerController.pause()
                        }else {
                            mediaPlayControllerUseCase.playAudio( myChannel = channels, index = index)
                        }
                    }

                }else {
                    if(currentPlayingChannel.value == null){
                        mediaPlayControllerUseCase.playAudio( myChannel = channels, index = index)
                    }else {
                        if(myChannel.stationuuid == currentPlayingChannel.value?.stationuuid){
                            mediaPlayControllerUseCase.playerController.singlePlay()
                        }else {
                            mediaPlayControllerUseCase.playAudio( myChannel = channels, index = index)
                        }
                    }

                }
               /* if (myChannel.stationuuid == currentPlayingChannel.value?.stationuuid) {
                    mediaPlayControllerUseCase.playerController.pause()
                } else {
                    mediaPlayControllerUseCase.playAudio(myChannel = channels, index = index)

                }*/
            }
        }

    }

    fun saveChannel(myChannel: MyChannel) {
        viewModelScope.launch {
            if (!isChannelSavedUseCase.isChannelSaved(myChannel.stationuuid).first()) {
                saveChannelUseCase.saveChannel(myChannel = myChannel)
            } else {
                removeSaveChannelUseCase.removeSaveChannel(channelID = myChannel.stationuuid)
            }

        }
    }


    fun isBufferingChannel(myChannel: MyChannel): StateFlow<Boolean> {

        return flow {
            mediaPlayControllerUseCase.playerController.isLoading.collect { isPlaying ->
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
                myContext.getSimCountry().ifEmpty { "USA" })
                .collect { channelList ->
                    channelMutableStateFlow.emit(channelList)
                }
        }
    }

    private fun getAllLocalChannel() {
        viewModelScope.launch {
            val response = countryListUseCase.getCountryList()
            response.collect { response ->
                when (response) {
                    is Resource.Success -> {

                    }

                    is Resource.Error -> {

                    }

                    is Resource.Loading -> {

                    }
                }
            }

        }
    }

    private fun getChannelByCountry() {
        viewModelScope.launch {

            val response = searchChannelUseCase.getChannelBySearch(countryCode =
                myContext.getSimCountry().ifEmpty { "USA" }, order = null, tag = null)
            response.collect { response ->
                when (response) {
                    is Resource.Success -> {
                    }

                    is Resource.Error -> {
                        if (localChannelUseCase.getChannelByCountryCode(countryCode = myContext.getSimCountry().ifEmpty { "USA" }).first().isEmpty()) {
                            MyCustomLogger.logMessageInfo(tag = TAG, message = "error message ${response.message}")
                            _uiState.emit(
                                UiState(
                                    error = response.message
                                )
                            )
                        }
                    }

                    is Resource.Loading -> {
                        if (localChannelUseCase.getChannelByCountryCode(countryCode = myContext.getSimCountry().ifEmpty { "USA" }).first().isEmpty()) {
                            _uiState.update {
                                UiState(
                                    isInitialLoading = true
                                )
                            }
                        }
                    }
                }
            }

        }
    }

}