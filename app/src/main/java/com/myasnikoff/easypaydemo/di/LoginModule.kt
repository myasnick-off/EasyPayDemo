package com.myasnikoff.easypaydemo.di

import com.myasnikoff.easypaydemo.domain.login.LoginRepository
import com.myasnikoff.easypaydemo.domain.login.LoginRepositoryImpl
import com.myasnikoff.easypaydemo.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    viewModel { LoginViewModel(get()) }
}