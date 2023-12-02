package com.myasnikoff.easypaydemo.login.domain

import com.myasnikoff.easypaydemo.core.data.model.TokenData
import com.myasnikoff.easypaydemo.core.domain.ApiResult

interface LoginRepository {
    suspend fun login(name: String, pass: String): ApiResult<TokenData>

    suspend fun logout()
}
