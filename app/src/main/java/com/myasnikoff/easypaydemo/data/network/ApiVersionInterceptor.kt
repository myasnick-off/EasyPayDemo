package com.myasnikoff.easypaydemo.data.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiVersionInterceptor(private val apiVersion: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val requestWithApiVersion = originRequest.newBuilder()
            .header(API_VERSION_NAME, apiVersion)
            .build()
        return chain.proceed(requestWithApiVersion)
    }

    companion object {
        private const val API_VERSION_NAME = "v"
    }
}
