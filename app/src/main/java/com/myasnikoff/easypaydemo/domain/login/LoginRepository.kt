package com.myasnikoff.easypaydemo.domain.login

import com.myasnikoff.easypaydemo.data.model.TokenData
import com.myasnikoff.easypaydemo.domain.ApiResult

interface LoginRepository {
    suspend fun login(name: String, pass: String): ApiResult<TokenData>

    suspend fun logout()
}
