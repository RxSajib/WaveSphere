package com.zenbyte.studio.domain.usecase.local

import com.zenbyte.studio.domain.repository.local.LocalChannelRepo
import javax.inject.Inject

class LocalChannelUseCase @Inject constructor(val localChannelRepo: LocalChannelRepo) {

    suspend fun getLocalChannelList() = localChannelRepo.getAllChannelFromLocal()

    suspend fun getChannelByCountry(countryName : String) = localChannelRepo.getChannelByCountry(countryName = countryName)

    suspend fun getChannelByCountryCode(countryCode : String) = localChannelRepo.getChannelByCountryCode(countryCode = countryCode)

    suspend fun getChannelByTags(tags : String, country : String) = localChannelRepo.getChannelByTags(tags = tags, country = country)

    fun getChannelLengthByLanguages(languages : String) = localChannelRepo.getChannelLengthByLanguages(languages = languages)

    fun getChannelsByLanguages(languages : String) = localChannelRepo.getChannelByLanguages(languages = languages)
}