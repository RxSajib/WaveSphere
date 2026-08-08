package com.zenbyte.studio.domain.model

data class MyGenres(
    val title : String,
    val icon : Int,
    val color : Int,
    val stationsCount : Int = 0
)
