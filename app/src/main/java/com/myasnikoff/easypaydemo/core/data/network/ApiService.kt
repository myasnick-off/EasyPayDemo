package com.myasnikoff.easypaydemo.core.data.network

import com.myasnikoff.easypaydemo.core.data.model.ApiResponse
import com.myasnikoff.easypaydemo.core.data.model.AuthData
import com.myasnikoff.easypaydemo.core.data.model.Payment
import com.myasnikoff.easypaydemo.core.data.model.TokenData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("/api-test/login")
    suspend fun login(
        @Body authData: AuthData
    ): ApiResponse<TokenData>

    @GET("/api-test/payments")
    suspend fun getPayments(
        @Header("token") token: String
    ): ApiResponse<List<Payment>>

    companion object {
        private const val BASE_URL = "https://easypay.world"
        private const val APP_KEY_VALUE = "12345"
        private const val API_VERSION_VALUE = "1"


        fun getInstance(): ApiService {
            val retrofit = Retrofit.Builder()
                .client(HttpClient.getInstance(APP_KEY_VALUE, API_VERSION_VALUE))
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}
