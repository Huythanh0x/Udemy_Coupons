package com.batdaulaptrinh.udemycoupons.util

import android.annotation.SuppressLint
import android.util.Log
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
            Log.i("TIME TAG", currentDate.toString())
            val diff: Long = endDate.time - currentDate.time + 7 * 60 * 60 * 1000
            return when {
                TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS) > 24 -> {
                    "${TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)} DAYS LEFT"
                }
                TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS) in 1..60 -> {
                    "${TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS)} MINUTES LEFT"

                }
                TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS) in 1..24 -> {

                    "${TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS)} HOURS LEFT"
                }
                else -> {
                    "Time error $diff"
                }
            }
        }
    }
}