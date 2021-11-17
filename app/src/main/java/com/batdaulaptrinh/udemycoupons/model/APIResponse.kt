package com.batdaulaptrinh.udemycoupons.model

data class APIResponse(
    var last_time_update: String,
    var results: List<CouponItem>
)