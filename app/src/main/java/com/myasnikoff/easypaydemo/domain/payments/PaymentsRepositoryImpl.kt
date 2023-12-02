package com.myasnikoff.easypaydemo.domain.payments

import com.myasnikoff.easypaydemo.data.model.Payment
import com.myasnikoff.easypaydemo.data.network.ApiService
import com.myasnikoff.easypaydemo.domain.ApiResult
import com.myasnikoff.easypaydemo.domain.BaseRepository
import com.myasnikoff.easypaydemo.domain.TokenStore

class PaymentsRepositoryImpl(
    private val apiService: ApiService,
    private val tokenStore: TokenStore
) : BaseRepository(), PaymentsRepository {

    override suspend fun getPayments(): ApiResult<List<Payment>> {
        return handleResponse(executeResponse = { apiService.getPayments(tokenStore.token) })
    }
}
