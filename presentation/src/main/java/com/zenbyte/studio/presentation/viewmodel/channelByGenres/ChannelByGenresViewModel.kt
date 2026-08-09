package com.zenbyte.studio.presentation.viewmodel.channelByGenres

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigator
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.model.MyGenres
import com.zenbyte.studio.domain.repository.local.LocalChannelRepo
import com.zenbyte.studio.domain.result.Resource
import com.zenbyte.studio.domain.usecase.MediaPlayControllerUseCase
import com.zenbyte.studio.presentation.viewmodel.state.ApiState
import com.zenbyte.studio.presentation.viewmodel.utils.Extras
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class ChannelByGenresViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val localChannelRepo: LocalChannelRepo,
    private val mediaPlayControllerUseCase: MediaPlayControllerUseCase
) : ViewModel() {


    var currentPlayingChannel = mediaPlayControllerUseCase.playerController.currentChannel

    var tagsNameMutableStateFlow = MutableStateFlow("")
    fun inputTag(tagName : String){
            tagsNameMutableStateFlow.update { tagName}
    }


    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val channelList = tagsNameMutableStateFlow.filter { it.isNotEmpty() }.distinctUntilChanged().flatMapLatest { tagName ->
        localChannelRepo.getChannelByTags(tags = tagName, country = Extras.getSimCountry(context = context))
    }.map { channelList ->
        ApiState(data = channelList, isSuccess = true, isLoading = false)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ApiState(isLoading = true, data = emptyList(), isSuccess = false)
    )


}