package com.myasnikoff.easypaydemo.ui.payments

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myasnikoff.easypaydemo.domain.ApiResult
import com.myasnikoff.easypaydemo.domain.payments.PaymentsRepository
import com.myasnikoff.easypaydemo.domain.payments.PaymentsRepositoryImpl
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PaymentsViewModel(
    private val repository: PaymentsRepository = PaymentsRepositoryImpl()
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
            val result = repository.getPayments()
            mPaymentsStateFlow.value = when (result) {
                is ApiResult.Failure.AuthError -> {
                    log(result.message)
                    PaymentsState.AuthFailure
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

    private fun log(message: String?) {
        Log.d(this.javaClass.simpleName, message.orEmpty())
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}