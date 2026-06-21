package com.zenbyte.studio.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zenbyte.studio.data.local.dao.FavoriteChannelDao
import com.zenbyte.studio.data.local.entity.MyFavoriteChannel

@Database(entities = [MyFavoriteChannel::class], version = 1, exportSchema = false)
abstract class MyFavoriteChannelDatabase : RoomDatabase() {

    abstract fun favoriteChannelDao(): FavoriteChannelDao
}