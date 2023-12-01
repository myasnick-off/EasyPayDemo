package com.myasnikoff.easypaydemo.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.textfield.TextInputLayout

fun View.hideSoftKeyboard() {
    this.findFocus()?.clearFocus()
    val imm = this.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun TextInputLayout.clearErrorMessage() {
    error = null
    isErrorEnabled = false
}

fun TextInputLayout.setErrorMessage(isEnabled: Boolean, stringResId: Int) {
    isErrorEnabled = isEnabled
    error = if (isEnabled) this.resources.getString(stringResId) else null
}