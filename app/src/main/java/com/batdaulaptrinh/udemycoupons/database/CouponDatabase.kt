package com.batdaulaptrinh.udemycoupons.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.batdaulaptrinh.udemycoupons.database.dao.CouponDAO
import com.batdaulaptrinh.udemycoupons.model.CouponItem

@Database(entities = [CouponItem::class], version = 1, exportSchema = false)
abstract class CouponDatabase : RoomDatabase() {
    abstract fun couponDAO(): CouponDAO
}