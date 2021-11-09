package com.batdaulaptrinh.udemycoupons.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.batdaulaptrinh.udemycoupons.data.database.CouponDAO
import com.batdaulaptrinh.udemycoupons.data.api.CouponService

open class HomeViewModelFactory(
    private val couponDAO: CouponDAO,
    private val couponService: CouponService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(couponDAO, couponService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 