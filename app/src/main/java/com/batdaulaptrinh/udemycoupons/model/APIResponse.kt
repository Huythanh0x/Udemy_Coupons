package com.batdaulaptrinh.udemycoupons.model

data class APIResponse(
    var recordsTotal: Int,
    var recordsFiltered:Int,
    var pages:Int,
    var results:List<CouponItem>
) {
}