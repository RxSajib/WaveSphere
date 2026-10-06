package com.zenbyte.studio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zenbyte.studio.data.remote.model.CountryDtoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MyCountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(countryDtoItem: List<CountryDtoItem>)

    @Query("SELECT * FROM CountryDB")
    fun getAllCountry() : Flow<List<CountryDtoItem>>

    @Query("DELETE FROM CountryDB")
    suspend fun deleteAllCountryData()
}