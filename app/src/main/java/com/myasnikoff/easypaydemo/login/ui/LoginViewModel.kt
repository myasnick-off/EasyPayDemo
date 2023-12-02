package com.myasnikoff.easypaydemo.login.ui

import android.text.Editable
import androidx.lifecycle.viewModelScope
import com.myasnikoff.easypaydemo.core.domain.ApiResult
import com.myasnikoff.easypaydemo.core.ui.BaseViewModel
import com.myasnikoff.easypaydemo.login.domain.LoginRepository
import com.myasnikoff.easypaydemo.login.ui.model.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : BaseViewModel() {

    private val mAuthStateFlow = MutableStateFlow<AuthState>(AuthState.Initial)
    val authStateFlow = mAuthStateFlow.asStateFlow()

    fun login(login: Editable?, pass: Editable?) {
        job?.cancel()
        job = viewModelScope.launch {
            val name = login.toNonNullString()
            val password = pass.toNonNullString()
            if (name.isEmpty() || password.isEmpty()) {
                mAuthStateFlow.value = AuthState.FieldFailure(
                    isEmptyName = name.isEmpty(),
                    isEmptyPass = password.isEmpty()
                )
            } else {
                mAuthStateFlow.value = AuthState.Loading
                val result = repository.login(name = name, pass = password)
                mAuthStateFlow.value = when (result) {
                    is ApiResult.Success -> {
                        AuthState.Success
                    }
                    is ApiResult.Failure.LoginError -> {
                        log(result.message)
                        AuthState.AuthFailure
                    }
                    is ApiResult.Failure -> {
                        log(result.message)
                        AuthState.UnknownFailure
                    }
                }
            }
        }
    }

    private fun Editable?.toNonNullString(): String =
        if (this.isNullOrBlank()) "" else this.toString()
}