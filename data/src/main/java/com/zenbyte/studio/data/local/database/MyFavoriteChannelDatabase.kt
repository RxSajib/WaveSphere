package com.zenbyte.studio.data.local.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.zenbyte.studio.data.local.dao.FavoriteChannelDao
import com.zenbyte.studio.data.local.entity.MyFavoriteChannel

@Database(
    entities = [MyFavoriteChannel::class],
    version = 5,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4),
        AutoMigration(from = 4, to = 5)
    ]
)
abstract class MyFavoriteChannelDatabase : RoomDatabase() {

    abstract fun favoriteChannelDao(): FavoriteChannelDao
}