package com.batdaulaptrinh.udemycoupons.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class TimeLeft {
    companion object {
        @SuppressLint("SimpleDateFormat")
        fun getTimeLeft(endDateString: String): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val endDate: Date = sdf.parse(endDateString)
            val currentDate = Calendar.getInstance().time
            val diff: Long = endDate.time - currentDate.time
            return if (diff < 2L) {
                "${TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS)} HOURS LEFT"
            } else {
                "${TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)} DAYS LEFT"
            }
        }
    }
}