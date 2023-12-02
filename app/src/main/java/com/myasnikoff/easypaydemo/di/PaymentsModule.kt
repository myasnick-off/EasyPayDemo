package com.myasnikoff.easypaydemo.di

import com.myasnikoff.easypaydemo.domain.payments.PaymentsRepository
import com.myasnikoff.easypaydemo.domain.payments.PaymentsRepositoryImpl
import com.myasnikoff.easypaydemo.ui.payments.PaymentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val paymentsModule = module {
    single<PaymentsRepository> { PaymentsRepositoryImpl(get()) }
    viewModel { PaymentsViewModel(get()) }
}