package com.zenbyte.studio.domain.model

data class UiState(
    val data: List<Any> = emptyList(),
    val isInitialLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)
