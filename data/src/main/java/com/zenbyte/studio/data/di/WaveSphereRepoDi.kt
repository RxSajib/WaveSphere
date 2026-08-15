package com.zenbyte.studio.data.di

import com.zenbyte.studio.data.remote.repoimpl.AppSettingImpl
import com.zenbyte.studio.data.remote.repoimpl.local.LocalChannelRepoImpl
import com.zenbyte.studio.data.remote.repoimpl.WaveSphereImpl
import com.zenbyte.studio.domain.repository.AppSetting
import com.zenbyte.studio.domain.repository.local.LocalChannelRepo
import com.zenbyte.studio.domain.repository.WaveSphereRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WaveSphereRepoDi {

    @Binds
    @Singleton
    abstract fun bindWaveSphereRepo(
        waveSphereImpl: WaveSphereImpl
    ): WaveSphereRepo

    @Binds
    @Singleton
    abstract fun bindAppSetting(
        appSettingImpl: AppSettingImpl
    ): AppSetting

    @Binds
    @Singleton
    abstract fun bindLocalChannelRepo(localChannelRepoImpl: LocalChannelRepoImpl) : LocalChannelRepo
}
