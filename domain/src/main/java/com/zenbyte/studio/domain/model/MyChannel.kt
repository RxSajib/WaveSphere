package com.zenbyte.studio.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MyChannel(
    val stationuuid: String = "",
    val name : String = "",
    val codec: String = "",
    val country: String = "",
    val url : String = "",
    val urlResolved : String = "",
    val favicon : String = "",
    val language : String = "",
    val votes : Int = 0,
    val tags : String = ""
)