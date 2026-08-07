package com.zenbyte.studio.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zenbyte.studio.data.local.database.MyFavoriteChannelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext contexts: Context): MyFavoriteChannelDatabase {
        return Room.databaseBuilder(
            contexts,
            MyFavoriteChannelDatabase::class.java,
            "radio_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteChannelDao(database: MyFavoriteChannelDatabase) = database.favoriteChannelDao()

    @Provides
    @Singleton
    fun provideMyChannelDao(database: MyFavoriteChannelDatabase) = database.channelDao()
}