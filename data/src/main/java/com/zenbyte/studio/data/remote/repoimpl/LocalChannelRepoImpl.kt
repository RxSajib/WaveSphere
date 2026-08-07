package com.zenbyte.studio.data.remote.repoimpl

import com.zenbyte.studio.data.local.dao.MyChannelDao
import com.zenbyte.studio.data.local.mapper.LocalMapper.toMyChannel
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.repository.LocalChannelRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalChannelRepoImpl @Inject constructor(val dao: MyChannelDao) : LocalChannelRepo {
    override suspend fun getAllChannelFromLocal(): Flow<List<MyChannel>> {
        return dao.getChannel().map { it ->
            it.map { info -> info.toMyChannel()}
        }
    }

    override suspend fun getChannelByCountry(countryName: String): Flow<List<MyChannel>> {
        return dao.getChannelByCountry(country = countryName).map {
            it.map { info -> info.toMyChannel() }
        }
    }

    override suspend fun getChannelByCountryCode(countryCode: String): Flow<List<MyChannel>> {
        return dao.getChannelByCountryCode(countryCode = countryCode).map {
            it.map { info -> info.toMyChannel() }
        }
    }
}