package com.zenbyte.studio.domain.usecase

import com.zenbyte.studio.domain.repository.FavoriteChannelRepo
import javax.inject.Inject

class GetAllFavoriteChannelUseCase @Inject constructor(
    val favoriteChannelRepo: FavoriteChannelRepo
) {

    fun getFavoriteChannel() = favoriteChannelRepo.getAllFavoriteChannel()
}