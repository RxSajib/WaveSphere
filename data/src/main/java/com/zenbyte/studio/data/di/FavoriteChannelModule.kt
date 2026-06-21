package com.zenbyte.studio.data.di

import com.zenbyte.studio.data.local.dao.FavoriteChannelDao
import com.zenbyte.studio.data.repoimpl.FavoriteChannelRepoImpl
import com.zenbyte.studio.domain.repository.FavoriteChannelRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FavoriteChannelModule {

    @Provides
    fun provideFavoriteChannelRepo(favoriteChannelDao: FavoriteChannelDao): FavoriteChannelRepo =
        FavoriteChannelRepoImpl(
            favoriteChannelDao = favoriteChannelDao
        )
}