package com.myasnikoff.easypaydemo.payments.ui.model


sealed class PaymentsState {
    object Loading : PaymentsState()
    object EmptyData : PaymentsState()
    data class ValidData(val data: List<PaymentItem>) : PaymentsState()
    object Logout : PaymentsState()
    object UnknownFailure : PaymentsState()
}
