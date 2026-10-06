package com.zenbyte.studio.data.remote.repoimpl.local

import com.zenbyte.studio.data.local.dao.MyCountryDao
import com.zenbyte.studio.data.local.mapper.LocalMapper.toMyCountry
import com.zenbyte.studio.domain.model.MyCountry
import com.zenbyte.studio.domain.repository.local.CountryListLocal
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class CountryListLocalRepoImpl @Inject constructor(val countryDao: MyCountryDao) : CountryListLocal {
    override suspend fun getAllCountry(): Flow<List<MyCountry>> {
        return countryDao.getAllCountry().toMyCountry()
    }
}