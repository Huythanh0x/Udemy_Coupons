package com.batdaulaptrinh.udemycoupons.model

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coupon_table")
data class CouponItem(
    @PrimaryKey
    @SerializedName("CourseId") var CourseId: Int,
    @Nullable
    @SerializedName("Language") var Language: String?,
    @Nullable
    @SerializedName("Category") var Category: String?,
    @Nullable
    @SerializedName("Title") var Title: String?,
    @Nullable
    @SerializedName("Topic") var Topic: String?,
    @Nullable
    @SerializedName("Level") var Level: String?,
    @Nullable
    @SerializedName("Author") var Author: String?,
    @SerializedName("Duration") var Duration: Double,
    @SerializedName("Rating") var Rating: Double,
    @SerializedName("Reviews") var Reviews: Int,
    @SerializedName("Students") var Students: Int,
    @SerializedName("CouponCode") var CouponCode: String,
    @SerializedName("ImageUrl") var ImageUrl: String,
    @SerializedName("CouponLink") var CouponLink: String,
    @SerializedName("CreatedD") var CreatedD: String,
    @SerializedName("ValidatedD") var ValidatedD: String,
    @SerializedName("EndTime") var EndTime: String,
    @SerializedName("Featured") var Featured: Boolean,
    @SerializedName("Prio") var Prio: Int

)