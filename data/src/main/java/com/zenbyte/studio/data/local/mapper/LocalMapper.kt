package com.zenbyte.studio.data.local.mapper

import com.zenbyte.studio.data.local.dao.MyChannelDao
import com.zenbyte.studio.data.local.entity.MyChannelEntity
import com.zenbyte.studio.data.local.model.Genres
import com.zenbyte.studio.data.remote.model.ChannelDtoItem
import com.zenbyte.studio.data.remote.model.CountryDtoItem
import com.zenbyte.studio.domain.model.MyChannel
import com.zenbyte.studio.domain.model.MyCountry
import com.zenbyte.studio.domain.model.MyGenres
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

object LocalMapper {


    fun ChannelDtoItem.toMyChannelEntity() : MyChannelEntity {
        return MyChannelEntity(
            stationuuid = stationuuid,
            name = name,
            codec = codec,
            country = country,
            url = url,
            urlResolved = url_resolved,
            countrycode = countrycode,
            favicon = favicon,
            language = language,
            votes = votes,
            tags = tags,
            lastcheckok = lastcheckok,
            sslError = ssl_error
        )
    }


    fun MyChannelEntity.toMyChannel() : MyChannel{
        return MyChannel(
            stationuuid = stationuuid,
            name = name,
            codec = codec,
            country = country,
            url = url,
            urlResolved = urlResolved,
            favicon = favicon,
            language = language,
            votes = votes,
            tags = tags,
            lastcheckok = lastcheckok,
            sslError = sslError
        )
    }

    fun Flow<List<CountryDtoItem>>.toMyCountry(): Flow<List<MyCountry>> {
        return map { countryList ->
            countryList.map { country ->
                MyCountry(
                    name = country.name,
                    countryCode = country.iso,
                    stationCount = country.stationcount
                )
            }
        }
    }
}