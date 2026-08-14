package com.zenbyte.studio.presentation.viewmodel.news

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.usecase.GetAllFavoriteChannelUseCase
import com.zenbyte.studio.domain.usecase.GetSingleSaveChannel
import com.zenbyte.studio.domain.usecase.RemoveSaveChannelUseCase
import com.zenbyte.studio.domain.usecase.SaveChannelUseCase
import com.zenbyte.studio.domain.usecase.local.LocalChannelUseCase
import com.zenbyte.studio.presentation.viewmodel.state.ApiState
import com.zenbyte.studio.presentation.viewmodel.utils.AppConst.NEWS_TAG
import com.zenbyte.studio.presentation.viewmodel.utils.Extras
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@HiltViewModel
class NewsChannelViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    val localChannelUseCase: LocalChannelUseCase,
    val getAllFavoriteChannelUseCase: GetAllFavoriteChannelUseCase,
    val getSingleSaveChannel: GetSingleSaveChannel,
    val saveChannelUseCase: SaveChannelUseCase,
    val removeSaveChannelUseCase: RemoveSaveChannelUseCase
) : ViewModel() {

    private val newsListMutableStateFlow = MutableStateFlow< ApiState<List<MyChannel>>>(ApiState(isLoading = true))
    val newsList = newsListMutableStateFlow.asStateFlow()
    var channelIDMutableStateFlow = MutableStateFlow<String>("")

    init {
        getNewsListByCountry()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val saveChannel = channelIDMutableStateFlow.flatMapLatest {
        getSingleSaveChannel.getSingleChannel(channelID = it)
    }

    private fun getNewsListByCountry(){
        viewModelScope.launch {
            localChannelUseCase.getChannelByTags(tags = NEWS_TAG, country = Extras.getSimCountry(context = context))
                .collect { channels ->
                    newsListMutableStateFlow.emit(ApiState(data = channels, isLoading = false, isSuccess = true))
                }
        }
    }
}