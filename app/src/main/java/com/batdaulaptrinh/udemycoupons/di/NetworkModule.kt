package com.batdaulaptrinh.udemycoupons.di

import com.batdaulaptrinh.udemycoupons.repository.CouponService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val netWorkModule = module {
    single<Retrofit> {
        Retrofit.Builder().baseUrl("https://teachinguide.azure-api.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create<CouponService>()
    }
}