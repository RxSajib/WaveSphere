package com.zenbyte.studio.data.local.Extras

import com.zenbyte.studio.data.remote.model.Language

object AppExtras {

    val languages = listOf(
        Language("en", "English", "English", isSelected = false),
        Language("bn", "Bengali", "বাংলা", isSelected = false),
        Language("hi", "Hindi", "हिन्दी", isSelected = false),
        Language("iw", "Hebrew", "עברית", isSelected = false),
        Language("ru", "Russian", "Русский", isSelected = false),
        Language("zh", "Chinese", "简体中文", isSelected = false)
    )
}