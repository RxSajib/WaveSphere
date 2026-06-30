package com.zenbyte.studio.domain.usecase

import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.repository.PlayerController
import javax.inject.Inject

class MediaPlayControllerUseCase @Inject constructor(
    val playerController: PlayerController
) {

    fun playAudio(myChannel: List<MyChannel>, index : Int){
        playerController.play(myChannel, index)
    }

    fun nextPlayBack(){
        playerController.next()
    }

}