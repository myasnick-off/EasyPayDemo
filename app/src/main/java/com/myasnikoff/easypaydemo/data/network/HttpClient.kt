package com.myasnikoff.easypaydemo.data.network

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object HttpClient {

    fun getInstance(appKey: String, apiVersion: String): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
            .addInterceptor(AppKeyInterceptor(appKey))
            .addInterceptor(ApiVersionInterceptor(apiVersion))
            .build()
    }

    private const val TIMEOUT_VALUE = 10L
}