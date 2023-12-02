package com.myasnikoff.easypaydemo.di

import com.myasnikoff.easypaydemo.data.network.ApiService
import org.koin.dsl.module

val appModule = module {
    single { ApiService.getInstance() }
}