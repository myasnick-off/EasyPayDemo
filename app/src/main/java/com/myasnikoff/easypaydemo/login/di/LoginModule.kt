package com.myasnikoff.easypaydemo.login.di

import com.myasnikoff.easypaydemo.login.domain.LoginRepository
import com.myasnikoff.easypaydemo.login.domain.LoginRepositoryImpl
import com.myasnikoff.easypaydemo.login.ui.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    single<LoginRepository> { LoginRepositoryImpl(get(), get()) }
    viewModel { LoginViewModel(get()) }
}
