package com.batdaulaptrinh.udemycoupons.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.batdaulaptrinh.udemycoupons.model.CouponItem

@Dao
interface CouponDAO {
    @Query("SELECT * FROM coupon_table")
    fun getAllCoupon(): LiveData<List<CouponItem>>

    @Insert
    fun addAllCoupon(allCoupons: List<CouponItem>)

    @Query("DELETE FROM coupon_table")
    fun deleteAllCoupon()

}