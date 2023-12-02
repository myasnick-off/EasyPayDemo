package com.myasnikoff.easypaydemo.core.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object FormatUtils {

    private val dateTimeFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale("ru", "ru"))
    private val amountFormat = DecimalFormat("###,###,###,###,###.00", DecimalFormatSymbols(Locale.ENGLISH))

    fun dateTime(date: Long?): String =
        date?.let { dateTimeFormat.format(Date(date)) } ?: ""

    fun amount(value: Double?): String =
        value?.let { amountFormat.format(value).replace(',', ' ') } ?: ""
}