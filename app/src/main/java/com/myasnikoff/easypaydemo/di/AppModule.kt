package com.myasnikoff.easypaydemo.di

import com.myasnikoff.easypaydemo.data.network.ApiService
import com.myasnikoff.easypaydemo.domain.TokenStore
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single { ApiService.getInstance() }
    single { TokenStore(androidApplication()) }
}
