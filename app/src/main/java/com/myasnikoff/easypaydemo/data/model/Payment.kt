package com.myasnikoff.easypaydemo.data.model

import com.google.gson.annotations.SerializedName

data class Payment(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String?,
    @SerializedName("amount") val amount: Double?,
    @SerializedName("created") val createdDate: Long?
)
