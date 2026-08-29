package com.zenbyte.studio.domain.repository.local

import kotlinx.coroutines.flow.Flow

interface DataStoreRepo {

    suspend fun saveStringData(key : String, value : String)

    fun getStringData(key : String) : Flow<String?>

    suspend fun saveBooleanData(key : String, value : Boolean)

    fun getBooleanData(key : String) : Flow<Boolean>

    suspend fun saveIntData(key : String, value : Int)

    fun getIntData(key : String) : Flow<Int?>

}