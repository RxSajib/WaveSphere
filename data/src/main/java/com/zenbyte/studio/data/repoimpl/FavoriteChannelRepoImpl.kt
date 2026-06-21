package com.zenbyte.studio.data.repoimpl

import com.zenbyte.studio.data.local.dao.FavoriteChannelDao
import com.zenbyte.studio.data.mapper.toDomain
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.repository.FavoriteChannelRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteChannelRepoImpl @Inject constructor(val favoriteChannelDao: FavoriteChannelDao) :
    FavoriteChannelRepo {
    override fun getAllFavoriteChannel(): Flow<List<MyChannel>> {
        return favoriteChannelDao.getFavoriteChannel().map { list ->
            list.map { it.toDomain() }
        }
    }
}