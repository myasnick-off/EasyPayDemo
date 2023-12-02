package com.myasnikoff.easypaydemo.ui.login

import android.text.Editable
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myasnikoff.easypaydemo.domain.ApiResult
import com.myasnikoff.easypaydemo.domain.login.LoginRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    private val mAuthStateFlow = MutableStateFlow<AuthState>(AuthState.Initial)
    val authStateFlow = mAuthStateFlow.asStateFlow()

    private var job: Job? = null

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

    private fun log(message: String?) {
        Log.d(this.javaClass.simpleName, message.orEmpty())
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}