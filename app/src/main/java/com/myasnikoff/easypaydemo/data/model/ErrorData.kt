package com.myasnikoff.easypaydemo.data.model

import com.google.gson.annotations.SerializedName

data class ErrorData(
    @SerializedName("error_code") val code: Int,
    @SerializedName("error_msg") val message: String?
)
