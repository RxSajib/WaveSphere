package com.zenbyte.studio.domain.repository

import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.model.MyCountry
import com.zenbyte.studio.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WaveSphereRepo {
    suspend fun getChannelByCountry(country: String): Flow<Resource<List<MyChannel>>>

    suspend fun getCountryList(): Flow<Resource<List<MyCountry>>>

    suspend fun getChannelBySearch(
        tag: String?,
        order: String?,
        countryCode: String?,
        hideBroken: Boolean?
    ): Flow<Resource<List<MyChannel>>>

    suspend fun getAllRadioStations() : Flow<Resource<List<MyChannel>>>

}
