package com.myasnikoff.easypaydemo.di

import com.myasnikoff.easypaydemo.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel(get()) }
}