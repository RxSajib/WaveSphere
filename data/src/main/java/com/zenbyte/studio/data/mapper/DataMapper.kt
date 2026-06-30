package com.zenbyte.studio.data.mapper

import com.zenbyte.studio.data.local.entity.MyFavoriteChannel
import com.zenbyte.studio.data.model.ChannelDtoItem
import com.zenbyte.studio.data.model.CountryDtoItem
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.model.MyCountry


fun ChannelDtoItem.toDomain(): MyChannel {
    return MyChannel(
        stationuuid = this.stationuuid,
        codec = this.codec,
        country = this.country,
        url = this.url,
        urlResolved = this.url_resolved,
        favicon = this.favicon,
        language = this.language,
        votes = this.votes,
        tags = this.tags,
        name = this.name,
        lastcheckok = this.lastcheckok,
        sslError = this.ssl_error,
    )
}

fun MyFavoriteChannel.toDomain(): MyChannel {
    return MyChannel(
        stationuuid = this.stationuuid,
        codec = this.codec,
        country = this.country,
        url = this.url,
        urlResolved = this.urlResolved,
        favicon = this.favicon,
        language = this.language,
        votes = this.votes,
        tags = this.tags,
        name = this.name,
        lastcheckok = this.lastcheckok,
        sslError = this.ssl_error
    )
}

fun MyChannel.toEntity(): MyFavoriteChannel {
    return MyFavoriteChannel(
        stationuuid = this.stationuuid,
        codec = this.codec,
        country = this.country,
        url = this.url,
        urlResolved = this.urlResolved,
        favicon = this.favicon,
        language = this.language,
        votes = this.votes,
        tags = this.tags,
        name = this.name
    )
}

fun CountryDtoItem.toDomain(): MyCountry {
    return MyCountry(
        name = this.name,
        stationCount = this.stationcount,
        countryCode = this.iso
    )
}
