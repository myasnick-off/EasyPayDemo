package com.myasnikoff.easypaydemo.payments.di

import com.myasnikoff.easypaydemo.payments.domain.PaymentsRepository
import com.myasnikoff.easypaydemo.payments.domain.PaymentsRepositoryImpl
import com.myasnikoff.easypaydemo.payments.ui.PaymentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val paymentsModule = module {
    single<PaymentsRepository> { PaymentsRepositoryImpl(get(), get()) }
    viewModel { PaymentsViewModel(get(), get()) }
}
