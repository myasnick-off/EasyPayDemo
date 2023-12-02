package com.myasnikoff.easypaydemo.core.domain

import android.content.Context

class TokenStore(context: Context) {

    private val prefs = context.getSharedPreferences(TOKEN_PREFS, Context.MODE_PRIVATE)

    var token: String
        get() = prefs.getString(TOKEN_KEY, null).orEmpty()
        set(value) = prefs.edit().putString(TOKEN_KEY, value).apply()

    fun clear() {
        token = ""
    }

    companion object {
        private const val TOKEN_PREFS = "token_prefs"
        private const val TOKEN_KEY = "token_key"
    }
}