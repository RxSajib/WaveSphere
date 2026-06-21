package com.zenbyte.studio.domain.repository

import com.zenbyte.studio.domain.model.MyChannel
import kotlinx.coroutines.flow.Flow

interface FavoriteChannelRepo {

    fun getAllFavoriteChannel() : Flow<List<MyChannel>>
}