package com.zenbyte.studio.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Channel_db")
data class MyChannelEntity(
    @PrimaryKey(autoGenerate = false)
    val stationuuid: String = "",
    val name : String = "",
    val codec: String = "",
    val country: String = "",
    val url : String = "",
    val urlResolved : String = "",
    val favicon : String = "",
    val language : String = "",
    val votes : Int = 0,
    val tags : String = "",
    val lastcheckok : Int = 0,
    val sslError : Int = 0,
    @ColumnInfo(defaultValue = "")
    val countrycode : String = ""
)