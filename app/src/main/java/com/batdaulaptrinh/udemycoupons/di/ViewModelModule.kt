package com.batdaulaptrinh.udemycoupons.di

import com.batdaulaptrinh.udemycoupons.ui.viewmodel.CouponViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CouponViewModel(get(), get()) }
}