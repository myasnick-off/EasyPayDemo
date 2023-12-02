package com.myasnikoff.easypaydemo.core.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AppKeyInterceptor(private val appKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val requestWithAppKey = originRequest.newBuilder()
            .header(APP_KEY_HEADER_NAME, appKey)
            .build()
        return chain.proceed(requestWithAppKey)
    }

    companion object {
        private const val APP_KEY_HEADER_NAME = "app-key"

    }
}
