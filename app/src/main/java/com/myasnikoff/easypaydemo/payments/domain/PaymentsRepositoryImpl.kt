package com.myasnikoff.easypaydemo.payments.domain

import com.myasnikoff.easypaydemo.core.data.model.Payment
import com.myasnikoff.easypaydemo.core.data.network.ApiService
import com.myasnikoff.easypaydemo.core.domain.ApiResult
import com.myasnikoff.easypaydemo.core.domain.BaseRepository
import com.myasnikoff.easypaydemo.core.domain.TokenStore

class PaymentsRepositoryImpl(
    private val apiService: ApiService,
    private val tokenStore: TokenStore
) : BaseRepository(), PaymentsRepository {

    override suspend fun getPayments(): ApiResult<List<Payment>> {
        return handleResponse(executeResponse = { apiService.getPayments(tokenStore.token) })
    }
}
