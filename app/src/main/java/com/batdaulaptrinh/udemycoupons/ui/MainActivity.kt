package com.batdaulaptrinh.udemycoupons.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.work.*
import com.batdaulaptrinh.udemycoupons.R
import com.batdaulaptrinh.udemycoupons.databinding.ActivityMainBinding
import com.batdaulaptrinh.udemycoupons.notification.NotifyWorker
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        startWork()
    }
    /**
     * Constraints ensure that work is deferred until optimal conditions are met.
     *
     * A specification of the requirements that need to be met before a WorkRequest can run.
     * By default, WorkRequests do not have any requirements and can run immediately.
     * By adding requirements, you can make sure that work only runs in certain situations
     * - for example, when you have an undeterred network and are charging.
     */
    // For more details visit the link https://medium.com/androiddevelopers/introducing-workmanager-2083bcfc4712
    private fun createConstraints() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)  // if connected to WIFI
        .setRequiresCharging(false)
        .setRequiresBatteryNotLow(true)                 // if the battery is not low
        .build()

    /**
     * You can use any of the work request builder that are available to use.
     * We will you the PeriodicWorkRequestBuilder as we want to execute the code periodically.
     *
     * The minimum time you can set is 15 minutes. You can check the same on the below link.
     * https://developer.android.com/reference/androidx/work/PeriodicWorkRequest
     *
     * You can also set the TimeUnit as per your requirement. for example SECONDS, MINUTES, or HOURS.
     */
    // setting period to 15 Minutes
    private fun createWorkRequest() = PeriodicWorkRequestBuilder<NotifyWorker>(15, TimeUnit.MINUTES)
        .setConstraints(createConstraints())
        .build()

    private fun startWork() {

        /* enqueue a work, ExistingPeriodicWorkPolicy.KEEP means that if this work already exists, it will be kept
        if the value is ExistingPeriodicWorkPolicy.REPLACE, then the work will be replaced */
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "FavDish Notify Work", ExistingPeriodicWorkPolicy.KEEP,
                createWorkRequest()
            )
    }
}


