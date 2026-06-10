package com.zenbyte.studio.domain.model

data class MyChannel(
    val name : String,
    val codec: String,
    val country: String,
    val url : String,
    val urlResolved : String,
    val favicon : String,
    val language : String,
    val votes : Int,
    val tags : String
)