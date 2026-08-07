package com.zenbyte.studio.data.repoimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.zenbyte.studio.data.api.WaveSphereApi
import com.zenbyte.studio.data.mapper.toDomain
import com.zenbyte.studio.data.model.CountryDtoItem
import com.zenbyte.studio.data.utils.BaseRepository
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.model.MyCountry
import com.zenbyte.studio.domain.repository.WaveSphereRepo
import com.zenbyte.studio.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WaveSphereImpl @Inject constructor(val api: WaveSphereApi) : WaveSphereRepo,
    BaseRepository() {
    override suspend fun getChannelByCountry(country: String): Resource<List<MyChannel>> {
        return safeApiCall(
            apiCall = { api.getChannelsByCountry(countryName = country) },
            mapper = { dto -> dto.map { it.toDomain() } }
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
        return safeApiCall(apiCall = {api.getAllStations()}, mapper = {channelDtoItems ->
            channelDtoItems.map {
                it.toDomain()
            }
        })
    }
}
