package com.zenbyte.studio.presentation.viewmodel.favorite

import androidx.lifecycle.ViewModel
import com.zenbyte.studio.domain.usecase.GetAllFavoriteChannelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteChannelViewModel @Inject constructor(
    val getAllFavoriteChannelUseCase: GetAllFavoriteChannelUseCase
) : ViewModel() {

    val favoriteChannel = getAllFavoriteChannelUseCase.getFavoriteChannel()
}