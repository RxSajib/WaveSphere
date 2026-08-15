package com.zenbyte.studio.data.local.model

import kotlinx.serialization.Serializable

@Serializable
data class Languages(
    val title : String = "",
    val titleEnglish : String ="",
    val color : Int = 0,
    val stationsCount : Int = 0
)
