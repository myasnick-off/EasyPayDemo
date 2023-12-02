package com.myasnikoff.easypaydemo.main.di

import com.myasnikoff.easypaydemo.main.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel(get()) }
}