package com.myasnikoff.easypaydemo.data.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type

data class Payment(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String?,
    @JsonAdapter(PaymentAmountDeserializer::class)
    @SerializedName("amount") val amount: Double?,
    @SerializedName("created") val createdDate: Long?
)

class PaymentAmountDeserializer : JsonDeserializer<Double?> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Double? {
        val amountString = json?.asJsonPrimitive?.asString
        return amountString?.toDoubleOrNull()
    }
}
