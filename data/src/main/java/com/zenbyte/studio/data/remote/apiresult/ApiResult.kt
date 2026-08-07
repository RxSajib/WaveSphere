package com.zenbyte.studio.data.remote.apiresult

import com.zenbyte.studio.domain.result.Resource
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T> safeApiCall(
    apiCall: suspend () ->T
) : Resource<T> {
    return try {
        val result = apiCall.invoke()
        Resource.Success(data = result)
    }catch (e : IOException){
        Resource.Error(message = "Check your internet connection")
    }catch (e : HttpException){
        val errorBody = e.response()?.errorBody()?.string()
        when(e.code()){
            400 -> {
               return Resource.Error(message = "Bad Request message $errorBody")
            }
            401 -> {
                return Resource.Error(message = "Unauthorized $errorBody")
            }
            403 -> {
                return Resource.Error(message = "Forbidden $errorBody")
            }
            404 -> {
                return Resource.Error(message = "Resource not found $errorBody")
            }
            408 -> {
                return Resource.Error(message = "Request timeout $errorBody")
            }
            429 -> {
                return Resource.Error(message = "Too many requests $errorBody")
            }
            500 -> {
                return Resource.Error(message = "Internal server error $errorBody")
            }
            502 -> {
                return Resource.Error(message = "Bad Gateway $errorBody")
            }
            503 -> {
                return Resource.Error(message = "Server is unavailable $errorBody")
            }else -> {
                Resource.Error(message = "Something went wrong $errorBody")
            }
        }
    }catch (e : SocketTimeoutException){
        Resource.Error(message = "connection timeout")
    }catch (e : UnknownHostException){
        Resource.Error(message = "Check your internet connection")
    }
    catch (e : Exception){
        Resource.Error(message = "Something went wrong")
    }
}