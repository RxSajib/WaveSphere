package com.zenbyte.studio.data.repoimpl

import com.zenbyte.studio.data.api.WaveSphereApi
import com.zenbyte.studio.data.mapper.DataMapper.toDomain
import com.zenbyte.studio.data.utils.BaseRepository
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.repository.WaveSphereRepo
import com.zenbyte.studio.domain.utils.Resource
import javax.inject.Inject

class WaveSphereImpl @Inject constructor(val api: WaveSphereApi) : WaveSphereRepo, BaseRepository() {
    override suspend fun getChannelByCountry(country: String): Resource<List<MyChannel>> {
        return safeApiCall(
            apiCall = { api.getChannelsByCountry(countryName = country) },
            mapper = { dto -> dto.map { it.toDomain() } }
        )
    }
}
