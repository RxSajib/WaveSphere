package com.zenbyte.studio.presentation.viewmodel.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.usecase.GetAllFavoriteChannelUseCase
import com.zenbyte.studio.domain.usecase.GetSingleSaveChannel
import com.zenbyte.studio.domain.usecase.RemoveSaveChannelUseCase
import com.zenbyte.studio.domain.usecase.SaveChannelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteChannelViewModel @Inject constructor(
    val getAllFavoriteChannelUseCase: GetAllFavoriteChannelUseCase,
    val getSingleSaveChannel: GetSingleSaveChannel,
    val saveChannelUseCase: SaveChannelUseCase,
    val removeSaveChannelUseCase: RemoveSaveChannelUseCase
) : ViewModel() {

    val favoriteChannel = getAllFavoriteChannelUseCase.getFavoriteChannel()

    private var channelIdMutableStateFlow = MutableStateFlow("")
    val channelId = channelIdMutableStateFlow.asStateFlow()

    fun saveChannelID(channelID : String){
        viewModelScope.launch {
            channelIdMutableStateFlow.emit(channelID)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val channel = channelId.flatMapLatest { channelID ->
        getSingleSaveChannel.getSingleChannel(channelID = channelID)
    }

    fun saveChannel(myChannel: MyChannel){
        viewModelScope.launch {
            saveChannelUseCase.saveChannel(myChannel = myChannel)
        }
    }

    fun removeChannel(channelID : String){
        viewModelScope.launch {
            removeSaveChannelUseCase.removeSaveChannel(channelID = channelID)
        }
    }

}