package com.example.ricktestapp.retrofit

import com.example.ricktestapp.BuildConfig
import com.example.ricktestapp.utils.isLoggingInterceptorEnabled
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestClient {

    private const val API_URL = "https://rickandmortyapi.com/api/"
    private const val CONTENT_TYPE = "application/json"

    val instance = createApiService()

    private fun createApiService(): ApiInterface {

        //add logging for non production flavors
        val okHttpClientBuilder = OkHttpClient.Builder().also { builder ->
            builder.readTimeout(120, TimeUnit.SECONDS)
            builder.writeTimeout(120, TimeUnit.SECONDS)
            builder.connectTimeout(120, TimeUnit.SECONDS)
            builder.addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Accept-Language", "ENGLISH")
                    .addHeader("Content-Type", CONTENT_TYPE)
                    .addHeader("Accept", CONTENT_TYPE)
                    .build()
                return@addInterceptor chain.proceed(request)
            }
        }

        okHttpClientBuilder.isLoggingInterceptorEnabled = BuildConfig.DEBUG

        return Retrofit.Builder().also { builder ->
            builder.client(okHttpClientBuilder.build())
            builder.baseUrl(API_URL)
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            builder.addConverterFactory(GsonConverterFactory.create())
        }.build().create(ApiInterface::class.java)

    }

}