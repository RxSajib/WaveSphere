package com.zenbyte.studio.domain.repository

import com.zenbyte.studio.domain.model.AppLanguages
import kotlinx.coroutines.flow.Flow

interface AppSetting {

    fun getLanguages() : Flow<List<AppLanguages>>
}