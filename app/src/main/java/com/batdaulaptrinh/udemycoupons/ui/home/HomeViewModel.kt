package com.batdaulaptrinh.udemycoupons.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.batdaulaptrinh.udemycoupons.data.api.CouponService
import com.batdaulaptrinh.udemycoupons.data.database.CouponDAO
import com.batdaulaptrinh.udemycoupons.model.APIResponse
import com.batdaulaptrinh.udemycoupons.model.CouponItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class HomeViewModel(private val couponDAO: CouponDAO, private val couponService: CouponService) :
    ViewModel() {
    var showCouponList: LiveData<List<CouponItem>> = couponDAO.getCouponContainKeyword("")

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
                        couponDAO.deleteAllCoupon()
                        couponDAO.addAllCoupon(coupons)
                    }
                }
            }

            override fun onFailure(call: Call<APIResponse?>, t: Throwable) {
                throw ExceptionInInitializerError()
            }


        })
    }

    fun getAllCoupons() = couponDAO.getAllCoupon()
    fun getCouponContainKeyword(formattedQuery: String) {
        showCouponList = couponDAO.getCouponContainKeyword(formattedQuery)
        Log.i("TAG OUTPUT SEARCH", showCouponList.value.toString())
    }

}