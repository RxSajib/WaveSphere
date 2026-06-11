package com.zenbyte.studio.domain.model

import java.util.Locale
import java.util.Locale.getDefault

data class MyCountry(
    val name : String,
    val countryCode : String,
    val stationCount : Int
){
    val countryFlag = "https://flagcdn.com/w320/${countryCode.lowercase(getDefault())}.png"
}
