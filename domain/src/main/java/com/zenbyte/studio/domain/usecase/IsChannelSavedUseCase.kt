package com.zenbyte.studio.domain.usecase

import com.zenbyte.studio.domain.repository.FavoriteChannelRepo
import javax.inject.Inject

class IsChannelSavedUseCase @Inject constructor(val favoriteChannelRepo: FavoriteChannelRepo) {

    fun isChannelSaved(stationuuid: String) = favoriteChannelRepo.isSavedChannel(stationuuid = stationuuid)
}