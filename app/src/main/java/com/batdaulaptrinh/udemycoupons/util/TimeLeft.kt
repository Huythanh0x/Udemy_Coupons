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
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val endDate: Date = sdf.parse(endDateString)
            val currentDate = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid")).time
            Log.i("TIME TAG", currentDate.toString())
            var diff: Long = endDate.time - currentDate.time
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
                    "Will be soon"
                }
            }
        }

        fun getTimeUpdate(lastTimeUpdate: String): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            sdf.timeZone = TimeZone.getTimeZone("Europe/Madrid")
            val lastUpdate: Date = sdf.parse(lastTimeUpdate)
            val currentDate = Calendar.getInstance().time
            Log.i("TIME TAG", currentDate.toString())
            var diff: Long = currentDate.time - lastUpdate.time
            return when {
                TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS) > 24 -> {
                    "${TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)} DAYS AGO"
                }
                TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS) in 1..60 -> {
                    "${TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS)} MINUTES AGO"

                }
                TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS) in 1..24 -> {

                    "${TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS)} HOURS AGO"
                }
                else -> {
                    "Recently"
                }
            }
        }

        fun isLessThan1Hour(lastTimeUpdate: String): Boolean {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            sdf.timeZone = TimeZone.getTimeZone("Europe/Madrid")
            val lastUpdate: Date = sdf.parse(lastTimeUpdate)
            val currentDate = Calendar.getInstance().time
            Log.i("TIME TAG", currentDate.toString())
            var diff: Long = currentDate.time - lastUpdate.time
            return TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS) > 1
        }
    }
}