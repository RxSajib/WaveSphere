package com.zenbyte.studio.domain.usecase

import com.zenbyte.studio.domain.repository.FavoriteChannelRepo
import javax.inject.Inject

class RemoveSaveChannelUseCase @Inject constructor(val favoriteChannelRepo: FavoriteChannelRepo) {

    suspend fun removeSaveChannel(channelID : String) = favoriteChannelRepo.removeChannel(channelID = channelID)
}