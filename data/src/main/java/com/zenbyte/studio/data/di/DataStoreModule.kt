package com.zenbyte.studio.data.di

import android.content.Context
import com.zenbyte.studio.data.local.repoimpl.DataStoreRepoImpl
import com.zenbyte.studio.domain.repository.local.DataStoreRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStoreRepo =
        DataStoreRepoImpl(context = context)

}