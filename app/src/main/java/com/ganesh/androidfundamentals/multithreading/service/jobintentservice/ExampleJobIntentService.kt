package com.ganesh.androidfundamentals.multithreading.service.jobintentservice

import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import androidx.core.app.JobIntentService

/***
 * Unlike IntentService, we do not need to handle wakelock part here, JobIntentService takes case of that
 */
class ExampleJobIntentService : JobIntentService() {

    //alternate of Java's static final constant
    companion object {
        private const val TAG: String = "ExampleJobIntentService"

        fun enqueueWork(context: Context, work: Intent) {
            enqueueWork(context, ExampleJobIntentService::class.java, 123, work)
        }
    }


    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    /***
     * it is the equivalent of onHandleIntent from IntentService and same as IntentService this method will be called sequentially for intents
     * in background thread. For below API 26 as an IntentService and above that as a JobService
     */
    override fun onHandleWork(intent: Intent) {
        Log.d(TAG, "onHandleWork")
        val input: String? = intent.getStringExtra("inputExtra")
        for (i in 1..10) {
            Log.d(TAG, "$input - $i")
            if (isStopped) {
                return
            }
            SystemClock.sleep(1000)
        }
    }

    /***
     * For API above 26, when JobScheduler has finished its execution, this method will be called
     * It can also be called when system kills a job specially in memory crunch scenarios or when the job has taken more than 10 minutes.
     * The return type is to handle the deferred execution, default return value if true meaning it will start the job later but we can
     * override it to return false which would not start it later
     *
     * When this method gives callback we need to handle this explicitly in our onHandleWork() method otherwise system will kill our service
     * When this method gives callback wakelock is also released so if we don't stop out onHandleWork() method it might misbehave but we don't
     * need to handle this using boolean logic as JobIntentService gives us a method isStopped() which returns true if control has been through
     * onStopCurrentWork()
     */
    override fun onStopCurrentWork(): Boolean {
        return super.onStopCurrentWork()
//        return false              //in this case the all the intents will be dropped and service will not resumed later
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

}