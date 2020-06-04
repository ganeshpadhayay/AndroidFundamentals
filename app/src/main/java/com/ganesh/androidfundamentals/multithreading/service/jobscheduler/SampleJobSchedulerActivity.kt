package com.ganesh.androidfundamentals.multithreading.service.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.R

class SampleJobSchedulerActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SampleServiceActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_service)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun scheduleJob(view: View) {
        var componentName: ComponentName = ComponentName(this, ExampleJobService::class.java)
        var jobInfo: JobInfo = JobInfo.Builder(123, componentName)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .setPersisted(true)                            //setPersisted(true) handles the starting of service even after boot of device
            .setPeriodic(15 * 60 * 10000)                //can't be set top lower than 15 minutes
            .build()

        var scheduler: JobScheduler =
            getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        var resultCode: Int =
            scheduler?.schedule(jobInfo)                //for api less than 21 it could be null
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled successfully")
        } else {
            Log.d(TAG, "Job scheduling failed")
        }

    }

    fun cancelJob(view: View) {
        var scheduler: JobScheduler =
            getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.cancel(123)
        Log.d(TAG, "Job cancelled")
    }
}

/***
JobScheduler only works on and above API 21, Below this we can use AlarmManager + Broadcast Receiver combination
Also, we can use WorkManager to simplify things as under the hood it does the same thing.
 **/