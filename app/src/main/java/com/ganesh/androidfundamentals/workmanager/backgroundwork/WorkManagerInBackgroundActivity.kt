package com.ganesh.androidfundamentals.workmanager.backgroundwork

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
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

        val request: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<MyWorker>()
                .setInitialDelay(3, TimeUnit.SECONDS)
//                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(this).enqueueUniqueWork("my-unique-name", ExistingWorkPolicy.KEEP, request)
    }

    private fun cancelWork() {
        WorkManager.getInstance(this).cancelUniqueWork("my-unique-name")
    }
}