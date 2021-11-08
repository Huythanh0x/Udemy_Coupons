package com.batdaulaptrinh.udemycoupons

import android.app.Application
import com.batdaulaptrinh.udemycoupons.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        configureDI()
    }

    private fun configureDI() = startKoin{
        androidContext( this@App)
        modules(appComponent)
    }
}