package com.zenbyte.studio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zenbyte.studio.data.local.entity.MyFavoriteChannel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteChannelDao{

    @Query("SELECT * FROM FavoriteChannelDB")
    fun getFavoriteChannel() : Flow<List<MyFavoriteChannel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChannel(channel: MyFavoriteChannel)

    @Query("DELETE FROM FavoriteChannelDB WHERE stationuuid = :stationuuid")
    suspend fun deleteChannel(stationuuid: String)

    @Query("SELECT * FROM FavoriteChannelDB WHERE stationuuid = :stationuuid")
    fun getChannel(stationuuid: String) : Flow<MyFavoriteChannel>


    @Query("SELECT EXISTS (SELECT 1 FROM FavoriteChannelDB WHERE stationuuid = :stationuuid)")
    fun isChannelSaved(stationuuid: String) : Flow<Boolean>
}
