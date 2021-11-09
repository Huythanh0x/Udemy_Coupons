package com.batdaulaptrinh.udemycoupons.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.batdaulaptrinh.udemycoupons.data.database.CouponDAO
import com.batdaulaptrinh.udemycoupons.data.api.CouponService

open class CouponViewModelFactory(
    private val couponDAO: CouponDAO,
    private val couponService: CouponService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CouponViewModel::class.java)) {
            return CouponViewModel(couponDAO, couponService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 