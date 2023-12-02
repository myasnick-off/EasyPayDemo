package com.myasnikoff.easypaydemo.core

import android.app.Application
import com.myasnikoff.easypaydemo.core.di.appModule
import com.myasnikoff.easypaydemo.login.di.loginModule
import com.myasnikoff.easypaydemo.main.di.mainModule
import com.myasnikoff.easypaydemo.payments.di.paymentsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EasyPaymentsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@EasyPaymentsApp)
            modules(appModule, mainModule, loginModule, paymentsModule)
        }
    }
}