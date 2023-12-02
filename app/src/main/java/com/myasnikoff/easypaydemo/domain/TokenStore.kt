package com.myasnikoff.easypaydemo.domain

import android.content.Context

class TokenStore(context: Context) {

    private val prefs = context.getSharedPreferences(TOKEN_PREFS, Context.MODE_PRIVATE)

    var token: String
        get() = prefs.getString(TOKEN_KEY, null).orEmpty()
        set(value) = prefs.edit().putString(TOKEN_KEY, value).apply()

    companion object {
        private const val TOKEN_PREFS = "token_prefs"
        private const val TOKEN_KEY = "token_key"
    }
}