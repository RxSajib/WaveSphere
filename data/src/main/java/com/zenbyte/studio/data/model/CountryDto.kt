package com.zenbyte.studio.data.model

import com.google.gson.annotations.SerializedName


class CountryDto : ArrayList<CountryDtoItem>()

data class CountryDtoItem(
    @SerializedName("iso_3166_1")
    val iso: String,
    val name: String,
    val stationcount: Int
)