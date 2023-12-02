package com.myasnikoff.easypaydemo.payments.ui.model

import com.myasnikoff.easypaydemo.core.data.model.Payment
import com.myasnikoff.easypaydemo.core.utils.FormatUtils

data class PaymentItem(
    val id: Long,
    val title: String,
    val dateString: String,
    val amountString: String
)

fun List<Payment>.toPaymentItems(): List<PaymentItem> {
    return map { payment ->
        PaymentItem(
            id = payment.id,
            title = payment.title.orEmpty(),
            dateString = FormatUtils.dateTime(payment.createdDate),
            amountString = FormatUtils.amount(payment.amount)
        )
    }.filterNot { it.amountString.isEmpty() }
}
