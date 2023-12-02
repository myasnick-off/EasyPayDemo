package com.myasnikoff.easypaydemo.core.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel() {

    protected var job: Job? = null

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }

    protected fun log(message: String?) {
        Log.d(this.javaClass.simpleName, message.orEmpty())
    }
}