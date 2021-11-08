package com.batdaulaptrinh.udemycoupons.repository

import com.batdaulaptrinh.udemycoupons.model.APIResponse
import com.batdaulaptrinh.udemycoupons.model.CouponItem
import retrofit2.Call
import retrofit2.http.*

interface CouponService {
    @GET("course-coupon?sortCol=featured&sortDir=DESC&length=12&page=1&inkw=&language=")
    fun get(): Call<APIResponse>

    @GET("/course-coupon?sortCol=featured&sortDir=DESC&length=100&page=1&inkw=&discount=100&language=")
    suspend fun getAPIResponse(): Call<APIResponse>

    @GET("/course-coupon?sortCol=featured&sortDir=DESC&page=1&inkw=&discount=100&language=")
    suspend fun getAPIResponseWithLength(@Query("length") length: Int): Call<APIResponse>

    @GET("/course-coupon?sortCol=featured&sortDir=DESC&page=1&inkw=&discount=100&language=")
    suspend fun getAPIResponseWithPath(@Path("sortCol") sort: String): Call<APIResponse>

    @POST("/course-coupon?sortCol=featured&sortDir=DESC&page=1&inkw=&discount=100&language=")
    suspend fun uploadCoupon(@Body newCoupons: CouponItem): Call<APIResponse>
}