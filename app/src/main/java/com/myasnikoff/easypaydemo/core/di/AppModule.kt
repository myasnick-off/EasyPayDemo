package com.myasnikoff.easypaydemo.core.di

import com.myasnikoff.easypaydemo.core.data.network.ApiService
import com.myasnikoff.easypaydemo.core.domain.TokenStore
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single { ApiService.getInstance() }
    single { TokenStore(androidApplication()) }
}
