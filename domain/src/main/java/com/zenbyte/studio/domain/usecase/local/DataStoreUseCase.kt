package com.zenbyte.studio.domain.usecase.local

import com.zenbyte.studio.domain.repository.local.DataStoreRepo
import javax.inject.Inject

class DataStoreUseCase @Inject constructor(
    val dataStoreRepo: DataStoreRepo
) {

    suspend fun saveStringData(key: String, value: String) =
        dataStoreRepo.saveStringData(key = key, value = value)

    fun getStringData(key : String) = dataStoreRepo.getStringData(key = key)

    suspend fun saveBooleanData(key : String, value : Boolean) = dataStoreRepo.saveBooleanData(key = key, value = value)

    fun getBoolData(key : String) = dataStoreRepo.getBooleanData(key = key)

    suspend fun saveIntData(key : String, value : Int) = dataStoreRepo.saveIntData(key = key, value = value)

    fun getIntData(key : String) = dataStoreRepo.getIntData(key = key)
}