package com.zenbyte.studio.data.remote.repoimpl

import android.util.Log
import com.zenbyte.studio.data.local.dao.MyChannelDao
import com.zenbyte.studio.data.local.mapper.LocalMapper.toMyChannelEntity
import com.zenbyte.studio.data.remote.api.WaveSphereApi
import com.zenbyte.studio.data.remote.mapper.toDomain
import com.zenbyte.studio.data.utils.BaseRepository
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.model.MyCountry
import com.zenbyte.studio.domain.repository.WaveSphereRepo
import com.zenbyte.studio.domain.utils.Resource
import javax.inject.Inject

class WaveSphereImpl @Inject constructor(val api: WaveSphereApi, val dao: MyChannelDao) : WaveSphereRepo,
    BaseRepository() {
    override suspend fun getChannelByCountry(country: String): Resource<List<MyChannel>> {
        return safeApiCall(
            apiCall = { api.getChannelsByCountry(countryName = country) },
            mapper = { dto -> dto.map { it.toDomain() } },
            saveToLocal = { channelDtoItems ->
                Log.d("CHANNEL", "getAllRadioStations: $channelDtoItems")
              //  dao.deleteAllChannel()
                dao.insertAllChannels(channelDtoItems.map { it.toMyChannelEntity() })
            }
        )
    }



    override suspend  fun getCountryList(): Resource<List<MyCountry>> {

        return safeApiCall(
            apiCall = {
                api.getCountryList()
            },
            mapper = { dto ->
                dto.map {
                    it.toDomain()
                }
            }
        )
    }

    override suspend fun getChannelBySearch(
        tag: String,
        order: String,
        countryCode: String,
        hideBroken: Boolean
    ): Resource<List<MyChannel>> {
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
            }
        )
    }

    override suspend fun getAllRadioStations(): Resource<List<MyChannel>> {
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
