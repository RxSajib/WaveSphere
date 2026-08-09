package com.zenbyte.studio.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MyGenres(
    val title : String = "",
    val titleEnglish : String ="",
    val icon : Int = 0,
    val color : Int = 0,
    val stationsCount : Int = 0
)
