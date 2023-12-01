package com.myasnikoff.easypaydemo.ui.login

sealed class AuthState {
    object Initial : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    data class AuthFailure(val message: String) : AuthState()
    data class UnknownFailure(val message: String) : AuthState()
}
