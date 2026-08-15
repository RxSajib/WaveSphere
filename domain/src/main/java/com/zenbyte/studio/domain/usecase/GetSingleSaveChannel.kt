package com.zenbyte.studio.domain.usecase

import com.zenbyte.studio.domain.repository.FavoriteChannelRepo
import javax.inject.Inject

class GetSingleSaveChannel @Inject constructor(val favoriteChannelRepo: FavoriteChannelRepo) {

    suspend fun getSingleChannel(channelID : String) = favoriteChannelRepo.getChannel(channelID = channelID)
}