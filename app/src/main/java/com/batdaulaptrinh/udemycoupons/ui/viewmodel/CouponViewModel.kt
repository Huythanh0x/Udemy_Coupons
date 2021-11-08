package com.batdaulaptrinh.udemycoupons.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.batdaulaptrinh.udemycoupons.database.dao.CouponDAO
import com.batdaulaptrinh.udemycoupons.model.APIResponse
import com.batdaulaptrinh.udemycoupons.repository.CouponService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class CouponViewModel(private val couponDAO: CouponDAO, private val couponService: CouponService) :
    ViewModel() {
    init {
        initNetworkRequest()
    }

    private fun initNetworkRequest() {
        val call = couponService.get()

        call.enqueue(object : Callback<APIResponse?> {
            override fun onResponse(call: Call<APIResponse?>, response: Response<APIResponse?>) {
                response.body()?.results?.let { coupons ->
                    thread {
                        Log.i("VIEW MODEL TAG", coupons.toString())
                        couponDAO.addAllCoupon(coupons)
                    }
                }x
            }

            override fun onFailure(call: Call<APIResponse?>, t: Throwable) {
                throw ExceptionInInitializerError()
            }


        })
    }

    fun getAllCoupons() = couponDAO.getAllCoupon()

}