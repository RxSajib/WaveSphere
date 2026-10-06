package com.zenbyte.studio.domain.repository.local

import com.zenbyte.studio.domain.model.MyCountry
import kotlinx.coroutines.flow.Flow

interface CountryListLocal {

    suspend fun getAllCountry() : Flow<List<MyCountry>>
}