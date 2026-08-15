package com.zenbyte.studio.domain.usecase

import com.zenbyte.studio.domain.repository.WaveSphereRepo
import javax.inject.Inject

class GetAllRadioStationsUseCase @Inject constructor(val waveSphereRepo: WaveSphereRepo) {

    suspend fun getAllRadioStations() = waveSphereRepo.getAllRadioStations()
}