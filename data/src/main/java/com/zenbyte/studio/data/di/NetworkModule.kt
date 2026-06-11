package com.zenbyte.studio.data.di

import com.zenbyte.studio.data.api.WaveSphereApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.zenbyte.studio.data.BuildConfig


private const val TAG = "NetworkModule"
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    val isDebug = BuildConfig.DEBUG
    @Provides
    @Singleton
    fun provideOkHttpClint() : OkHttpClient{

        val interceptor = HttpLoggingInterceptor().apply {
            if (isDebug) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        return OkHttpClient.Builder()
            .connectTimeout(300, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
            .writeTimeout(300, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .retryOnConnectionFailure(true)
            .addInterceptor {chain ->
                val original: Request = chain.request()
                val requestBuilder: Request.Builder =
                    original.newBuilder().addHeader("Connection", "keep-alive")
                        .addHeader("Accept", "*/*")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept-Language", "en")


                requestBuilder.method(original.method, original.body)
                val request: Request = requestBuilder.build()

                chain.proceed(request)
            }
            .build()
    }



    @Singleton
    @Provides
    fun provideWaveSphereApi() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(provideOkHttpClint())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideWaveSphereService(retrofit: Retrofit) : WaveSphereApi {
        return retrofit.create(WaveSphereApi::class.java)
    }

}