package com.ganesh.androidfundamentals.workmanager.backgroundwork

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.ganesh.androidfundamentals.R
import kotlinx.android.synthetic.main.activity_work_manager_in_background.*
import java.util.concurrent.TimeUnit

class WorkManagerInBackgroundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager_in_background)

        scheduleWork?.setOnClickListener {
            scheduleWork()
        }

        cancelWork?.setOnClickListener {
            cancelWork()
        }
    }

    private fun scheduleWork() {

//        val constraints: Constraints = Constraints.Builder().apply {
//            setRequiredNetworkType(NetworkType.CONNECTED)
//            setRequiresCharging(true)
//        }.build()

//        val oneTimeRequest: OneTimeWorkRequest.Builder = OneTimeWorkRequestBuilder<MyWorker>()
//            .setInitialDelay(3, TimeUnit.SECONDS)
//            .setConstraints(constraints)
//            .build()

        val periodicWorkRequest: PeriodicWorkRequest = PeriodicWorkRequestBuilder<MyWorker>(15, TimeUnit.MINUTES).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork("my-unique-name", ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequest)
    }

    private fun cancelWork() {
        WorkManager.getInstance(this).cancelUniqueWork("my-unique-name")
    }
}