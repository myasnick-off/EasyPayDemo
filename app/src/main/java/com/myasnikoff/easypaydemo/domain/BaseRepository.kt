package com.myasnikoff.easypaydemo.domain

import com.myasnikoff.easypaydemo.data.model.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.lang.IllegalStateException

abstract class BaseRepository {

    protected suspend fun <T> handleResponse(
        executeResponse: suspend () -> ApiResponse<T>,
        handleResult: (T) -> Unit = {}
    ): ApiResult<T> {
        return try {
            val result = withContext(Dispatchers.IO) { executeResponse() }
            if (result.success) {
                if (result.response == null) {
                    ApiResult.Failure.DataError(DEFAULT_EMPTY_DATA_MESSAGE)
                } else {
                    handleResult(result.response)
                    ApiResult.Success(data = result.response)
                }
            } else {
                when (result.error?.code) {
                    CREDENTIALS_ERROR_CODE -> {
                        ApiResult.Failure.LoginError(
                            code = result.error.code,
                            message = result.error.message
                        )
                    }

                    TOKEN_ERROR_CODE, APP_KEY_ERROR_CODE -> {
                        ApiResult.Failure.AuthError(
                            code = result.error.code,
                            message = result.error.message
                        )
                    }

                    VERSION_ERROR_CODE -> {
                        ApiResult.Failure.ApiError(
                            code = result.error.code,
                            message = result.error.message
                        )
                    }

                    else -> ApiResult.Failure.UnknownError(e = IllegalStateException())
                }
            }
        } catch (error: HttpException) {
            ApiResult.Failure.UnknownError(e = error)
        } catch (error: IOException) {
            ApiResult.Failure.UnknownError(e = error)
        }
    }

    companion object {
        private const val DEFAULT_EMPTY_DATA_MESSAGE = "No data!"
        private const val TOKEN_ERROR_CODE = 1001
        private const val CREDENTIALS_ERROR_CODE = 1003
        private const val APP_KEY_ERROR_CODE = 1006
        private const val VERSION_ERROR_CODE = 1002
    }
}
