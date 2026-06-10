package com.zenbyte.studio.data.api

import com.zenbyte.studio.data.model.ChannelDto
import retrofit2.http.GET
import retrofit2.http.Path

interface WaveSphereApi {

    @GET("stations/bycountry/{countryName}")
    suspend fun getChannelsByCountry(@Path ("countryName") countryName: String): ChannelDto

}