package com.myasnikoff.easypaydemo.main.ui

import androidx.lifecycle.viewModelScope
import com.myasnikoff.easypaydemo.core.domain.TokenStore
import com.myasnikoff.easypaydemo.core.ui.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val tokenStore: TokenStore
) : BaseViewModel() {

    private val mLoginStateFlow = MutableStateFlow(false)
    val loginStateFlow = mLoginStateFlow.asStateFlow()

    init {
        checkToken()
    }

    private fun checkToken() {
        job?.cancel()
        job = viewModelScope.launch {
            val token = tokenStore.token
            mLoginStateFlow.value = token.isNotEmpty()
        }
    }
}