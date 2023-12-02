package com.myasnikoff.easypaydemo.core.data.model

import com.google.gson.annotations.SerializedName

data class TokenData(
    @SerializedName("token") val token: String?
)
