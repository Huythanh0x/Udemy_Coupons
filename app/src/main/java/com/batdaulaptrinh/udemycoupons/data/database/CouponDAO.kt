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
    suspend fun addAllCoupon(allCoupons: List<CouponItem>)

    @Query("DELETE FROM coupon_table")
    suspend fun deleteAllCoupon()

    @Query("SELECT * FROM coupon_table WHERE LOWER(Title) LIKE :formattedQuery")
    suspend fun getCouponContainKeyword(formattedQuery: String): List<CouponItem>

}