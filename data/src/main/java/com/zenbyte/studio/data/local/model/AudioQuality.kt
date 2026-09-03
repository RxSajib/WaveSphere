package com.zenbyte.studio.data.local.model

data class AudioQuality(
    val title: String,
    val details: String,
    val isChecked: Boolean = false,
    val audioRate: String? = null,
)
