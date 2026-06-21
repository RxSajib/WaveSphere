package com.zenbyte.studio.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.zenbyte.studio.data.local.entity.MyFavoriteChannel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteChannelDao{

    @Query("SELECT * FROM FavoriteChannelDB")
    fun getFavoriteChannel() : Flow<List<MyFavoriteChannel>>

}