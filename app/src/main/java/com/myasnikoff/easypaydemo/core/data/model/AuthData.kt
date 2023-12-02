package com.myasnikoff.easypaydemo.core.data.model

import com.google.gson.annotations.SerializedName

data class AuthData(
    @SerializedName("login") val login: String,
    @SerializedName("password") val password: String
)
