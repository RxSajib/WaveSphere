package com.zenbyte.studio.data.remote.repoimpl

import android.util.Log
import com.zenbyte.studio.data.local.dao.MyChannelDao
import com.zenbyte.studio.data.local.dao.MyCountryDao
import com.zenbyte.studio.data.local.mapper.LocalMapper.toMyChannelEntity
import com.zenbyte.studio.data.remote.api.WaveSphereApi
import com.zenbyte.studio.data.remote.mapper.toDomain
import com.zenbyte.studio.data.utils.BaseRepository
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.model.MyCountry
import com.zenbyte.studio.domain.repository.WaveSphereRepo
import com.zenbyte.studio.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WaveSphereImpl @Inject constructor(val api: WaveSphereApi, val dao: MyChannelDao, val countryDao : MyCountryDao) : WaveSphereRepo,
    BaseRepository() {
    override suspend fun getChannelByCountry(country: String): Flow<Resource<List<MyChannel>>> {
        return safeApiCall(
            apiCall = { api.getChannelsByCountry(countryName = country) },
            mapper = { dto -> dto.map { it.toDomain() } },
            saveToLocal = { channelDtoItems ->
                dao.insertAllChannels(channelDtoItems.map { it.toMyChannelEntity() })
            }
        )
    }


    override suspend  fun getCountryList(): Flow<Resource<List<MyCountry>>> {

        return safeApiCall(
            apiCall = {
                api.getCountryList()
            },
            mapper = { dto ->
                dto.map {
                    it.toDomain()
                }
            },
            saveToLocal = { countryDtoItems ->
                countryDao.insertCountry(countryDtoItem = countryDtoItems)
            }
        )
    }

    override suspend fun getChannelBySearch(
        tag: String?,
        order: String?,
        countryCode: String?,
        hideBroken: Boolean?
    ): Flow<Resource<List<MyChannel>>> {
        return safeApiCall(
            apiCall = {
                api.getChannelBySearch(
                    tag = tag,
                    order = order,
                    countryCode = countryCode
                )
            },
            mapper = { dto ->
                dto.map {
                    it.toDomain()
                }
            },
            saveToLocal = { data ->
                dao.insertAllChannels(data.map {
                    it.toMyChannelEntity()
                })
            }
        )
    }

    override suspend fun getAllRadioStations(): Flow<Resource<List<MyChannel>>> {
        return safeApiCall(apiCall = { api.getAllStations() }, mapper = { channelDtoItems ->
            channelDtoItems.map {
                it.toDomain()
            }
        }, saveToLocal = { channelDtoItems ->
            Log.d("CHANNEL", "getAllRadioStations: $channelDtoItems")
           // dao.deleteAllChannel()
           // dao.insertAllChannels(channelDtoItems.map { it.toMyChannelEntity() })
        })
    }
}
