package com.zenbyte.studio.data.utils

import com.zenbyte.studio.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {
    fun <T, R> safeApiCall(
        apiCall: suspend () -> T,
        mapper: (T) -> R,
        saveToLocal: (suspend (T) -> Unit)? = null
    ): Flow<Resource<R>> = flow<Resource<R>> {
        emit(Resource.Loading())
        try {
            val response = apiCall.invoke()
            saveToLocal?.invoke(response)
            emit(Resource.Success(mapper(response)))
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> emit(Resource.Error("Network Failure: Please check your internet connection"))
                is HttpException -> {
                    val code = throwable.code()
                    emit(Resource.Error("HTTP Error $code: ${throwable.message()}"))
                }

                else -> emit(Resource.Error("Unknown Error: ${throwable.localizedMessage}"))
            }
        }
    }.flowOn(Dispatchers.IO)
}
