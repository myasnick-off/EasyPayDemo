package com.myasnikoff.easypaydemo.core.domain

sealed interface ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>
    sealed class Failure(open val message: String?) : ApiResult<Nothing> {
        data class LoginError(val code: Int, override val message: String?) : Failure(message)
        data class AuthError(val code: Int, override val message: String?) : Failure(message)
        data class ApiError(val code: Int, override val message: String?) : Failure(message)
        data class DataError(override val message: String?) : Failure(message)
        data class UnknownError(val e: Throwable) : Failure(e.message)
    }
}
