package com.batdaulaptrinh.udemycoupons.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.batdaulaptrinh.udemycoupons.model.CouponItem

@Database(entities = [CouponItem::class], version = 1, exportSchema = false)
abstract class CouponDatabase : RoomDatabase() {
    abstract fun couponDAO(): CouponDAO


    companion object {
        @Volatile
        private var INSTANCE: CouponDatabase? = null
        fun getDatabase(context: Context): CouponDatabase {
            val temInstance = INSTANCE
            if (temInstance != null) {
                return temInstance
            }
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, CouponDatabase::class.java, "udemy_coupon")
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
