package com.batdaulaptrinh.udemycoupons.data.repository

import androidx.lifecycle.LiveData
import com.batdaulaptrinh.udemycoupons.data.api.CouponService
import com.batdaulaptrinh.udemycoupons.data.database.CouponDAO
import com.batdaulaptrinh.udemycoupons.model.APIResponse
import com.batdaulaptrinh.udemycoupons.model.CouponItem
import retrofit2.Call

class CouponRepository(private val couponDAO: CouponDAO, private val couponService: CouponService) {
    fun fetchDataFromAPI(): Call<APIResponse> = couponService.get()
    fun getAllCoupon(): LiveData<List<CouponItem>> = couponDAO.getAllCoupon()
    suspend fun getCouponContainKeyword(formattedString: String): List<CouponItem> =
        couponDAO.getCouponContainKeyword(formattedString)

    fun get() = couponService.get()
    suspend fun deleteAllCoupon() = couponDAO.deleteAllCoupon()
    suspend fun addAllCoupon(coupons: List<CouponItem>) = couponDAO.addAllCoupon(coupons)
}