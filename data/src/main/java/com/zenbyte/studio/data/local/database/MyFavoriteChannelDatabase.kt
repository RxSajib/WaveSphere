package com.zenbyte.studio.data.local.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.zenbyte.studio.data.local.dao.FavoriteChannelDao
import com.zenbyte.studio.data.local.dao.MyChannelDao
import com.zenbyte.studio.data.local.entity.MyChannelEntity
import com.zenbyte.studio.data.local.entity.MyFavoriteChannel

@Database(
    entities = [MyFavoriteChannel::class, MyChannelEntity::class],
    version = 8,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4),
        AutoMigration(from = 4, to = 5),
        AutoMigration(from = 5, to = 6),
        AutoMigration(from = 6, to = 7),
        AutoMigration(from = 7, to = 8, spec = MyFavoriteChannelDatabase.MyAutoMigration::class),
    ]
)


abstract class MyFavoriteChannelDatabase : RoomDatabase() {

    @RenameTable(fromTableName = "Channel.bd", toTableName = "Channel_db")
    class MyAutoMigration : AutoMigrationSpec

    abstract fun favoriteChannelDao(): FavoriteChannelDao

    abstract fun channelDao(): MyChannelDao
}