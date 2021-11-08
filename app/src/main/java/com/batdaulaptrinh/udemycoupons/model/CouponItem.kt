package com.batdaulaptrinh.udemycoupons.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coupon_table")
data class CouponItem(
    @PrimaryKey
    @SerializedName("CourseId") var CourseId: Int,
    @SerializedName("CouponId") var CouponId: Int,
    @SerializedName("Language") var Language: String,
    @SerializedName("Category") var Category: String,
    @SerializedName("Subcategory") var Subcategory: String,
    @SerializedName("SubcategoryId") var SubcategoryId: Int,
    @SerializedName("Title") var Title: String,
    @SerializedName("Topic") var Topic: String,
    @SerializedName("Level") var Level: String,
    @SerializedName("TopicId") var TopicId: Int,
    @SerializedName("Author") var Author: String,
    @SerializedName("AuthorId") var AuthorId: String,
    @SerializedName("Duration") var Duration: Double,
    @SerializedName("Rating") var Rating: Double,
    @SerializedName("Reviews") var Reviews: Int,
    @SerializedName("Students") var Students: Int,
    @SerializedName("DiscountP") var DiscountP: Int,
    @SerializedName("Link") var Link: String,
    @SerializedName("CouponCode") var CouponCode: String,
    @SerializedName("ImageUrl") var ImageUrl: String,
    @SerializedName("CouponLink") var CouponLink: String,
    @SerializedName("PriceDiscounted") var PriceDiscounted: Int,
    @SerializedName("PriceOld") var PriceOld: Double,
    @SerializedName("CreatedD") var CreatedD: String,
    @SerializedName("ValidatedD") var ValidatedD: String,
    @SerializedName("EndTime") var EndTime: String,
    @SerializedName("Featured") var Featured: Boolean,
    @SerializedName("Prio") var Prio: Int

)