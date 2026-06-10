package com.zenbyte.studio.data.utils

import com.zenbyte.studio.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {
    suspend fun <T, R> safeApiCall(
        apiCall: suspend () -> T,
        mapper: (T) -> R
    ): Resource<R> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiCall.invoke()
                Resource.Success(mapper(response))
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> Resource.Error("Network Failure: Please check your internet connection")
                    is HttpException -> {
                        val code = throwable.code()
                        Resource.Error("HTTP Error $code: ${throwable.message()}")
                    }
                    else -> Resource.Error("Unknown Error: ${throwable.localizedMessage}")
                }
            }
        }
    }
}
