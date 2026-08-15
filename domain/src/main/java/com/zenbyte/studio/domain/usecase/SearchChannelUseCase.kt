package com.zenbyte.studio.domain.usecase

import com.zenbyte.studio.domain.repository.WaveSphereRepo
import javax.inject.Inject

class SearchChannelUseCase @Inject constructor(
    val repo: WaveSphereRepo
) {

    suspend fun getChannelBySearch(  tag: String,
                             order: String,
                             countryCode: String,
                             hideBroken: Boolean = true) = repo.getChannelBySearch(tag, order, countryCode, hideBroken)
}