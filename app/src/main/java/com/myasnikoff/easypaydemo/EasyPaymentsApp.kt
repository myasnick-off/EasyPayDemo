package com.myasnikoff.easypaydemo

import android.app.Application
import com.myasnikoff.easypaydemo.di.appModule
import com.myasnikoff.easypaydemo.di.loginModule
import com.myasnikoff.easypaydemo.di.paymentsModule
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
            modules(appModule, loginModule, paymentsModule)
        }
    }
}