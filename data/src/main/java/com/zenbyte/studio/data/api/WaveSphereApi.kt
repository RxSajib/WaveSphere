package com.zenbyte.studio.data.api

import com.zenbyte.studio.data.model.ChannelDto
import com.zenbyte.studio.data.model.CountryDto
import com.zenbyte.studio.data.model.CountryDtoItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WaveSphereApi {

    @GET("stations/bycountrycodeexact/{countryName}")
    suspend fun getChannelsByCountry(@Path("countryName") countryName: String): ChannelDto

    @GET("countries")
    suspend fun getCountryList(): CountryDto

    @GET("stations/search")
    suspend fun getChannelBySearch(
        @Query("tag") tag: String,
        @Query("codec") codec: String = "mp3",
        @Query("order") order: String,
        @Query("countrycode") countryCode: String,
        @Query("hidebroken") hideBroken: Boolean = true
    ): ChannelDto

    @GET("stations")
    suspend fun getAllStations() : ChannelDto
}