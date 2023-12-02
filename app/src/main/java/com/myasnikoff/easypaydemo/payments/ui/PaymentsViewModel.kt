package com.myasnikoff.easypaydemo.payments.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myasnikoff.easypaydemo.core.domain.ApiResult
import com.myasnikoff.easypaydemo.login.domain.LoginRepository
import com.myasnikoff.easypaydemo.payments.domain.PaymentsRepository
import com.myasnikoff.easypaydemo.payments.ui.model.PaymentsState
import com.myasnikoff.easypaydemo.payments.ui.model.toPaymentItems
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PaymentsViewModel(
    private val paymentsRepository: PaymentsRepository,
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val mPaymentsStateFlow = MutableStateFlow<PaymentsState>(PaymentsState.Loading)
    val paymentsStateFlow = mPaymentsStateFlow.asStateFlow()

    private var job: Job? = null

    init {
        getPayments()
    }

    fun getPayments() {
        job = viewModelScope.launch {
            mPaymentsStateFlow.value = PaymentsState.Loading
            val result = paymentsRepository.getPayments()
            mPaymentsStateFlow.value = when (result) {
                is ApiResult.Failure.AuthError -> {
                    log(result.message)
                    PaymentsState.Logout
                }
                is ApiResult.Failure -> {
                    log(result.message)
                    PaymentsState.UnknownFailure
                }
                is ApiResult.Success -> {
                    if (result.data.isEmpty()) {
                        PaymentsState.EmptyData
                    } else {
                        PaymentsState.ValidData(data = result.data.toPaymentItems())
                    }
                }
            }
        }
    }

    fun logout() {
        job = viewModelScope.launch {
            mPaymentsStateFlow.value = PaymentsState.Loading
            loginRepository.logout()
            mPaymentsStateFlow.value = PaymentsState.Logout
        }
    }

    private fun log(message: String?) {
        Log.d(this.javaClass.simpleName, message.orEmpty())
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}