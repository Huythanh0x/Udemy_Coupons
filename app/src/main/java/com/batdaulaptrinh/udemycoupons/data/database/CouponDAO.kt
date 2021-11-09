package com.batdaulaptrinh.udemycoupons.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.batdaulaptrinh.udemycoupons.model.CouponItem

@Dao
interface CouponDAO {
    @Query("SELECT * FROM coupon_table")
    fun getAllCoupon(): LiveData<List<CouponItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllCoupon(allCoupons: List<CouponItem>)

    @Query("DELETE FROM coupon_table")
    fun deleteAllCoupon()

    @Query("SELECT * FROM coupon_table WHERE (Title) LIKE :formattedQuery")
    fun getCouponContainKeyword(formattedQuery: String):LiveData<List<CouponItem>>

}