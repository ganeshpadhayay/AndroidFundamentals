package com.ganesh.androidfundamentals.multithreading.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.SystemClock
import android.util.Log

class ExampleJobService : JobService() {

    companion object {
        private const val TAG = "ExampleJobService"
    }

    private var jobCancelled: Boolean = false

    //system invokes this method and this method runs on the UI thread so for long running task we need to create a thread here
    //if our task is short and can be done here, we return false
    //we return true when we do not want this service to finish, in this case we are responsible for thread creation and service termination
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "Job Started")
        doBackgroundWork(params)
        return true
    }

    //we are responsible for telling the system that out job has finished otherwise service won't terminate and eat up resources
    private fun doBackgroundWork(params: JobParameters?) {
        Thread(Runnable {
            for (i in 1..10) {
                Log.d(TAG, "run: $i")
                if (jobCancelled) {
                    return@Runnable
                }
                SystemClock.sleep(1000)
            }
            Log.d(TAG, "Job Finished")
            jobFinished(params, false)
        }).start()
    }

    //if we are using async task inside this jobService then we can call ansynTask.cancel() method here in onStopJob()
    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "Job cancelled without completion")
        jobCancelled = true
        return false        //return true if you need to retry this later
    }


}