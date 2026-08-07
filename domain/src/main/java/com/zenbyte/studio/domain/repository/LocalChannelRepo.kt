package com.zenbyte.studio.domain.repository

import com.zenbyte.studio.domain.model.MyChannel
import kotlinx.coroutines.flow.Flow

interface LocalChannelRepo {

    suspend fun getAllChannelFromLocal() : Flow<List<MyChannel>>

    suspend fun getChannelByCountry(countryName : String) : Flow<List<MyChannel>>

    suspend fun getChannelByCountryCode(countryCode : String) : Flow<List<MyChannel>>
}