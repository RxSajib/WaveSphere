package com.zenbyte.studio.domain.usecase

import com.zenbyte.studio.domain.repository.WaveSphereRepo
import javax.inject.Inject

class GetChannelByCountryUseCase @Inject constructor(
    val waveSphereRepo: WaveSphereRepo
) {

    suspend fun getChannelByCountry(countryName: String) =
        waveSphereRepo.getChannelByCountry(country = countryName)
}