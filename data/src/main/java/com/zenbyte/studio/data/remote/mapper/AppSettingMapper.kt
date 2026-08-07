package com.zenbyte.studio.data.remote.mapper

import com.zenbyte.studio.data.remote.model.Language
import com.zenbyte.studio.domain.model.AppLanguages

object AppSettingMapper {

    fun Language.toDomain(): AppLanguages = AppLanguages(code = this.code, name = this.name, this.nativeName, this.isSelected)

}