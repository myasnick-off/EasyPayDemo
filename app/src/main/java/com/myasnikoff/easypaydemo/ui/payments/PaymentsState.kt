package com.myasnikoff.easypaydemo.ui.payments


sealed class PaymentsState {
    object Loading : PaymentsState()
    object EmptyData : PaymentsState()
    data class ValidData(val data: List<PaymentItem>) : PaymentsState()
    object AuthFailure : PaymentsState()
    object UnknownFailure : PaymentsState()
}
