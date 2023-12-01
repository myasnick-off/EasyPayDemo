package com.myasnikoff.easypaydemo.domain.payments

import com.myasnikoff.easypaydemo.data.model.Payment
import com.myasnikoff.easypaydemo.domain.ApiResult

interface PaymentsRepository {
    suspend fun getPayments(): ApiResult<List<Payment>>
}
