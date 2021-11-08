package com.batdaulaptrinh.udemycoupons.di

import androidx.room.Room
import com.batdaulaptrinh.udemycoupons.database.CouponDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), CouponDatabase::class.java, "coupons_database")
            .build()
    }
    single {
        get<CouponDatabase>().couponDAO()
    }
}