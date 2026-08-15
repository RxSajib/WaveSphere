package com.zenbyte.studio.presentation.viewmodel.state

import androidx.annotation.Keep

@Keep
data class ApiState<T>(
    val isSuccess : Boolean= false,
    val isLoading : Boolean= false,
    val errorMessage : String?= null,
    val data: T?= null
)
