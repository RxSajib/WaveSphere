package com.zenbyte.studio.data.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


class CountryDto : ArrayList<CountryDtoItem>()

@Entity(tableName = "CountryDB")
data class CountryDtoItem(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("iso_3166_1")
    val iso: String = "",
    val name: String = "",
    val stationcount: Int = 0
)