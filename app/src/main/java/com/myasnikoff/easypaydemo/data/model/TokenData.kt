package com.myasnikoff.easypaydemo.data.model

import com.google.gson.annotations.SerializedName

data class TokenData(
    @SerializedName("token") val token: String?
    )
