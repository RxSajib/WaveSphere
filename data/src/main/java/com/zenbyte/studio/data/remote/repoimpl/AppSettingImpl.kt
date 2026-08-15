package com.zenbyte.studio.data.remote.repoimpl

import com.zenbyte.studio.data.local.Extras.AppExtras
import com.zenbyte.studio.data.remote.mapper.AppSettingMapper.toDomain
import com.zenbyte.studio.domain.model.AppLanguages
import com.zenbyte.studio.domain.repository.AppSetting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

class AppSettingImpl @Inject constructor() : AppSetting {
    override fun getLanguages(): Flow<List<AppLanguages>> {
        val languagesList = AppExtras.languages
        val map = languagesList.map { ln ->
            ln.toDomain()
        }
        return listOf(map).asFlow()
    }
}