package com.zenbyte.studio.wavesphere.di

import com.zenbyte.studio.domain.repository.PlayerController
import com.zenbyte.studio.wavesphere.player.Media3PlayerController
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MediaControllerModule {

    @Binds
    @Singleton
    abstract fun bindPlayerController(
        impl: Media3PlayerController
    ): PlayerController
}