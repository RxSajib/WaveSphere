package com.zenbyte.studio.wavesphere

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.wavesphere.app.MyApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MediaPlayerViewModel @Inject constructor() : ViewModel() {

    fun playMusic(myChannel: MyChannel) {
        try {
            val mediaItem = MediaItem.fromUri(myChannel.url)
            MyApplication.exoPlayer.apply {
                setMediaItem(mediaItem)
                prepare()
                play()
            }

        } catch (e: Exception) {

        }
    }
}