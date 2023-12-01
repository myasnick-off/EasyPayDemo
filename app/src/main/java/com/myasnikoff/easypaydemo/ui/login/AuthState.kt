package com.myasnikoff.easypaydemo.ui.login

sealed class AuthState {
    object Initial : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    data class FieldFailure(val isEmptyName: Boolean, val isEmptyPass: Boolean) : AuthState()
    object AuthFailure : AuthState()
    object UnknownFailure : AuthState()
}
