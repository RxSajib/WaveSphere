package com.zenbyte.studio.domain.repository

import com.zenbyte.studio.domain.model.MyChannel
import kotlinx.coroutines.flow.Flow

interface FavoriteChannelRepo {

    fun getAllFavoriteChannel() : Flow<List<MyChannel>>

    suspend fun deleteChannel(channelID : String)

    suspend fun saveChannel(myChannel: MyChannel)

    suspend fun getChannel(channelID : String) : Flow<MyChannel?>

    fun isSavedChannel(stationuuid: String): Flow<Boolean>

    suspend fun removeChannel(channelID: String)
}