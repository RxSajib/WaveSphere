package com.zenbyte.studio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zenbyte.studio.data.local.entity.MyChannelEntity
import com.zenbyte.studio.domain.model.MyChannel
import kotlinx.coroutines.flow.Flow

@Dao
interface MyChannelDao {

    @Query("SELECT * FROM channel_db")
    fun getChannel() : Flow<List<MyChannelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChannel(channel: MyChannelEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllChannels(channels: List<MyChannelEntity>)

    @Query("DELETE FROM channel_db")
    suspend fun deleteAllChannel()

    @Query("SELECT * FROM channel_db WHERE country like :country")
    fun getChannelByCountry(country: String) : Flow<List<MyChannelEntity>>

    @Query("SELECT * FROM channel_db WHERE countrycode like :countryCode")
    fun getChannelByCountryCode(countryCode: String) : Flow<List<MyChannelEntity>>

    @Query("""
        SELECT * FROM channel_db 
        WHERE (tags LIKE '%' || :tags || '%') 
        AND (
            countrycode = :country 
            OR 
            NOT EXISTS (SELECT 1 FROM channel_db WHERE (tags LIKE '%' || :tags || '%') AND countrycode = :country order by votes desc)
        )
    """)
    fun getChannelByTags(tags : String, country : String) : Flow<List<MyChannelEntity>>

}