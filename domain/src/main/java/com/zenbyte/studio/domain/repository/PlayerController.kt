package com.zenbyte.studio.domain.repository

import com.zenbyte.studio.domain.model.MyChannel
import kotlinx.coroutines.flow.StateFlow

interface PlayerController {

    fun play(channels: List<MyChannel>, startIndex: Int)

    fun singlePlay()

    fun pause()

    fun stop()

    fun next()

    fun previous()

    val currentChannel: StateFlow<MyChannel?>

    val isPlaying: StateFlow<Boolean>

    val isLoading: StateFlow<Boolean>
}