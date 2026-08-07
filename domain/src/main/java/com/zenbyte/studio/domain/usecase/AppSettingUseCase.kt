package com.zenbyte.studio.domain.usecase

import com.zenbyte.studio.domain.repository.AppSetting
import javax.inject.Inject

class AppSettingUseCase @Inject constructor(val appSetting: AppSetting) {

    fun getLanguages() = appSetting.getLanguages()
}