package com.trend.feature_common.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val baseUrl = "https://expertochevron.com/"

    private var mHttpLoggingInterceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private var mOkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(mHttpLoggingInterceptor)
        .build()

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}