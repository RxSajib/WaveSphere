package com.zenbyte.studio.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteChannelDB")
data class MyFavoriteChannel(
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
    @ColumnInfo(defaultValue = "")
    val ssl_error : Int = 0,
    @ColumnInfo(defaultValue = "")
    val lastcheckok : Int = 0

)
