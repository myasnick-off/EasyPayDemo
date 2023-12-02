package com.myasnikoff.easypaydemo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myasnikoff.easypaydemo.domain.TokenStore
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val tokenStore: TokenStore
) : ViewModel() {

    private val mLoginStateFlow = MutableStateFlow(false)
    val loginStateFlow = mLoginStateFlow.asStateFlow()

    private var job: Job? = null

    init {
        checkToken()
    }

    fun checkToken() {
        job?.cancel()
        job = viewModelScope.launch {
            val token = tokenStore.token
            mLoginStateFlow.value = token.isNotEmpty()
        }
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}