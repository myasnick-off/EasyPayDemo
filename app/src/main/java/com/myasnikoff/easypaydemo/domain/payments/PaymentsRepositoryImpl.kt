package com.myasnikoff.easypaydemo.domain.payments

import com.myasnikoff.easypaydemo.data.model.Payment
import com.myasnikoff.easypaydemo.data.network.ApiService
import com.myasnikoff.easypaydemo.domain.ApiResult
import com.myasnikoff.easypaydemo.domain.BaseRepository
import com.myasnikoff.easypaydemo.domain.TokenHolder

class PaymentsRepositoryImpl(
    private val apiService: ApiService = ApiService.getInstance()
) : BaseRepository(), PaymentsRepository {

    override suspend fun getPayments(): ApiResult<List<Payment>> {
        val token = TokenHolder.token
        return handleResponse(executeResponse = { apiService.getPayments(token) })
    }
}
