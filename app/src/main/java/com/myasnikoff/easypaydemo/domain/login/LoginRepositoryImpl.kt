package com.myasnikoff.easypaydemo.domain.login

import com.myasnikoff.easypaydemo.data.model.AuthData
import com.myasnikoff.easypaydemo.data.model.TokenData
import com.myasnikoff.easypaydemo.data.network.ApiService
import com.myasnikoff.easypaydemo.domain.ApiResult
import com.myasnikoff.easypaydemo.domain.BaseRepository
import com.myasnikoff.easypaydemo.domain.TokenHolder

class LoginRepositoryImpl(
    private val apiService: ApiService
) : BaseRepository(), LoginRepository {

    override suspend fun login(name: String, pass: String): ApiResult<TokenData> {
        return handleResponse(
            executeResponse = { apiService.login(authData = AuthData(name, pass)) },
            handleResult = { data -> saveToken(data.token.orEmpty()) }
        )
    }

    private fun saveToken(token: String) {
        TokenHolder.token = token
    }
}
