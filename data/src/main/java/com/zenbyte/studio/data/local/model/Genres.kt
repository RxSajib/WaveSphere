package com.zenbyte.studio.data.local.model

import kotlinx.serialization.Serializable

@Serializable
data class Genres(
    val title : String = "",
    val icon : Int = 1,
    val color : Int = 1,
    val stationsCount : Int = 0
)
