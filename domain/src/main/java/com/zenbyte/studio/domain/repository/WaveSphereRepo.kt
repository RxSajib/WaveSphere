package com.zenbyte.studio.domain.repository

import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.utils.Resource

interface WaveSphereRepo {
    suspend fun getChannelByCountry(country: String): Resource<List<MyChannel>>
}
