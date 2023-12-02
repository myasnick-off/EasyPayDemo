package com.myasnikoff.easypaydemo.core.data.network

import com.myasnikoff.easypaydemo.core.data.network.interceptor.ApiVersionInterceptor
import com.myasnikoff.easypaydemo.core.data.network.interceptor.AppKeyInterceptor
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