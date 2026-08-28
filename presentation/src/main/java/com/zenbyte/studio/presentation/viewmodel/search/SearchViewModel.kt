package com.zenbyte.studio.presentation.viewmodel.search

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.model.MyCountry
import com.zenbyte.studio.domain.model.MyGenres
import com.zenbyte.studio.domain.usecase.CountryListUseCase
import com.zenbyte.studio.domain.usecase.GetAllFavoriteChannelUseCase
import com.zenbyte.studio.domain.usecase.GetSingleSaveChannel
import com.zenbyte.studio.domain.usecase.IsChannelSavedUseCase
import com.zenbyte.studio.domain.usecase.MediaPlayControllerUseCase
import com.zenbyte.studio.domain.usecase.RemoveSaveChannelUseCase
import com.zenbyte.studio.domain.usecase.SaveChannelUseCase
import com.zenbyte.studio.domain.usecase.local.LocalChannelUseCase
import com.zenbyte.studio.domain.usecase.SearchChannelUseCase
import com.zenbyte.studio.domain.utils.Resource
import com.zenbyte.studio.presentation.viewmodel.state.ApiState
import com.zenbyte.studio.presentation.viewmodel.utils.AppConst.NEWS_TAG
import com.zenbyte.studio.presentation.viewmodel.utils.Extras
import com.zenbyte.studio.presentation.viewmodel.utils.Extras.getSimCountry
import com.zenbyte.studio.presentation.viewmodel.utils.MyCustomLogger
import com.zenbyte.studio.presentation.viewmodel.utils.localDataSources.GenresData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

private const val TAG = "SearchViewModel"
@HiltViewModel
class SearchViewModel @Inject constructor(
    val countryListUseCase: CountryListUseCase,
    val searchChannelUseCase: SearchChannelUseCase,
    val localChannelUseCase: LocalChannelUseCase,
    @ApplicationContext val context: Context,
    val getAllFavoriteChannelUseCase: GetAllFavoriteChannelUseCase,
    val getSingleSaveChannel: GetSingleSaveChannel,
    val saveChannelUseCase: SaveChannelUseCase,
    val removeSaveChannelUseCase: RemoveSaveChannelUseCase,
    val isChannelSavedUseCase: IsChannelSavedUseCase,
    val mediaPlayControllerUseCase: MediaPlayControllerUseCase
) : ViewModel() {

    private val searchInputMutableStateFlow = MutableStateFlow("")
    val searchInput = searchInputMutableStateFlow.asStateFlow()


    private val newsListMutableStateFlow = MutableStateFlow< ApiState<List<MyChannel>>>(ApiState(isLoading = true))
    val newsList = newsListMutableStateFlow.asStateFlow()

    fun getNewsList(countryCode : String){
        viewModelScope.launch {
            newsListMutableStateFlow.emit(ApiState(isLoading = true))
            val response = searchChannelUseCase.getChannelBySearch(tag = "news", order = "news", countryCode = countryCode)
            when(response){
                is Resource.Success -> {
                    MyCustomLogger.logMessageDebug(tag = TAG, message = response.data.toString())
                    newsListMutableStateFlow.emit(ApiState(data = response.data?: emptyList(), isLoading = false, isSuccess = true))
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {
                    newsListMutableStateFlow.emit(ApiState(errorMessage = response.message, isLoading = false, isSuccess = false))
                }
            }
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    fun getSaveChannel(channelID: String) =
        isChannelSavedUseCase.isChannelSaved(stationuuid = channelID)

    fun saveChannel(myChannel: MyChannel){
        viewModelScope.launch {
            if(!isChannelSavedUseCase.isChannelSaved(myChannel.stationuuid).first()){
                saveChannelUseCase.saveChannel(myChannel = myChannel)
            }else {
                removeSaveChannelUseCase.removeSaveChannel(channelID = myChannel.stationuuid)
            }

        }
    }



    fun inputSearchData(searchKey : String){
        viewModelScope.launch {
            searchInputMutableStateFlow.emit(searchKey)
        }
    }

    private var selectedMenuPositionMutableStateFlow = MutableStateFlow(1)
    val selectedMenuPosition = selectedMenuPositionMutableStateFlow.asStateFlow()

    private val countryListMutableStateFlow = MutableStateFlow<ApiState< List<MyCountry>>>(ApiState(isLoading = true))
    val countryState = countryListMutableStateFlow.asStateFlow()

    private val currentPayingChannelMutableStateFlow = MutableStateFlow<MyChannel?>(null)
    val currentPlayingChannel = currentPayingChannelMutableStateFlow.asStateFlow()
    var isMusicPlaying by mutableStateOf(false)

    fun setSelectedMenuPosition(position: Int){
        viewModelScope.launch {
            selectedMenuPositionMutableStateFlow.emit(position)
        }
    }

    init {
        getAllCountry()
       /* getNewsList(
            countryCode = "in"
        )*/
        getNewsListByCountry()
        getCurrentPlayingChannelInfo()
        getGenresData()
    }

    fun getGenresData() : List<MyGenres>{
        return GenresData.getGenres(context = context)
    }

    private fun getNewsListByCountry(){
        viewModelScope.launch {
            localChannelUseCase.getChannelByTags(tags = NEWS_TAG, country = context.getSimCountry())
                .collect { channels ->
                    newsListMutableStateFlow.emit(ApiState(data = channels, isLoading = false, isSuccess = true))
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

    fun isPlaying(myChannel: MyChannel) : StateFlow<Boolean>{
        return flow {
            mediaPlayControllerUseCase.playerController.isPlaying.collect { isPlaying ->
                if(myChannel.stationuuid == currentPlayingChannel.value?.stationuuid){
                    emit(isPlaying)
                    return@collect
                }else{
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

    fun getAllCountry(){
        viewModelScope.launch {
            countryListMutableStateFlow.emit(ApiState(isLoading = true))
            when(val response = countryListUseCase.getCountryList()){
                is Resource.Success -> {
                    countryListMutableStateFlow.emit(ApiState(data = response.data?: emptyList(), isSuccess = true))
                }
                is Resource.Error -> {
                    countryListMutableStateFlow.emit(ApiState(errorMessage = response.message, isSuccess = false))
                }
                is Resource.Loading -> {

                }
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
}