package com.myasnikoff.easypaydemo.payments.domain

import com.myasnikoff.easypaydemo.core.data.model.Payment
import com.myasnikoff.easypaydemo.core.domain.ApiResult

interface PaymentsRepository {
    suspend fun getPayments(): ApiResult<List<Payment>>
}
