package com.myasnikoff.easypaydemo.ui.login

import android.text.Editable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myasnikoff.easypaydemo.domain.ApiResult
import com.myasnikoff.easypaydemo.domain.login.LoginRepository
import com.myasnikoff.easypaydemo.domain.login.LoginRepositoryImpl
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository = LoginRepositoryImpl()
) : ViewModel() {

    private val mAuthStateFlow = MutableStateFlow<AuthState>(AuthState.Initial)
    val authStateFlow = mAuthStateFlow.asStateFlow()

    private var job: Job? = null

    fun login(login: Editable?, pass: Editable?) {
        job?.cancel()
        job = viewModelScope.launch {
            mAuthStateFlow.value = AuthState.Loading
            val result = repository.login(
                name = login.toNonNullString(),
                pass = pass.toNonNullString()
            )
            when (result) {
                is ApiResult.Success -> {}
                is ApiResult.Failure.AuthError -> {}
                else -> {}
            }
        }
    }

    private fun Editable?.toNonNullString(): String =
        if (this.isNullOrBlank()) "" else this.toString()
}