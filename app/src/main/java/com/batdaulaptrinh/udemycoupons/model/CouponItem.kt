package com.batdaulaptrinh.udemycoupons.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coupon_table")
data class CouponItem(
    @PrimaryKey
    @SerializedName("course_id") var courseId: String,
    @SerializedName("category") var category: String,
    @SerializedName("sub_category") var subCategory: String,
    @SerializedName("title") var title: String,
    @SerializedName("level") var level: String,
    @SerializedName("author") var author: String,
    @SerializedName("duration") var duration: String,
    @SerializedName("rating") var rating: String,
    @SerializedName("reviews") var reviews: String,
    @SerializedName("students") var students: String,
    @SerializedName("coupon_code") var couponCode: String,
    @SerializedName("preview_img") var previewImg: String,
    @SerializedName("coupon_link") var couponLink: String,
    @SerializedName("end_day") var endDay: String,
    @SerializedName("headline") var headline: String,
    @SerializedName("description") var description: String,
    @SerializedName("preview_video") var previewVideo: String


) {
    override fun toString(): String {
        return title
    }
}