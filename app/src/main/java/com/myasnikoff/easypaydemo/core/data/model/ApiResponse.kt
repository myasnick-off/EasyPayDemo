package com.myasnikoff.easypaydemo.core.data.model

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("success") val success: Boolean,
    @SerializedName("response") val response: T?,
    @SerializedName("error") val error: ErrorData?
)
