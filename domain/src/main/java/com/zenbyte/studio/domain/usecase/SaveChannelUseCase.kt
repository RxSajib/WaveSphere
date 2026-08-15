package com.zenbyte.studio.domain.usecase

import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.repository.FavoriteChannelRepo
import javax.inject.Inject

class SaveChannelUseCase @Inject constructor(val favoriteChannelRepo: FavoriteChannelRepo) {

    suspend fun saveChannel(myChannel: MyChannel) = favoriteChannelRepo.saveChannel(myChannel = myChannel)

}