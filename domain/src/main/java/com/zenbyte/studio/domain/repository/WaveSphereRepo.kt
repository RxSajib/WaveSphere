package com.zenbyte.studio.domain.repository

import androidx.paging.PagingData
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.model.MyCountry
import com.zenbyte.studio.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WaveSphereRepo {
    suspend fun getChannelByCountry(country: String): Resource<List<MyChannel>>

    suspend fun getCountryList(): Resource<List<MyCountry>>

    suspend fun getChannelBySearch(
        tag: String,
        order: String,
        countryCode: String,
        hideBroken: Boolean
    ): Resource<List<MyChannel>>
}
