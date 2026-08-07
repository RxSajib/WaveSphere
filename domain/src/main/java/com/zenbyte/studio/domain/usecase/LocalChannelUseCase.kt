package com.zenbyte.studio.domain.usecase

import com.zenbyte.studio.domain.repository.LocalChannelRepo
import javax.inject.Inject

class LocalChannelUseCase @Inject constructor(val localChannelRepo: LocalChannelRepo) {

    suspend fun getLocalChannelList() = localChannelRepo.getAllChannelFromLocal()

    suspend fun getChannelByCountry(countryName : String) = localChannelRepo.getChannelByCountry(countryName = countryName)

    suspend fun getChannelByCountryCode(countryCode : String) = localChannelRepo.getChannelByCountryCode(countryCode = countryCode)
}