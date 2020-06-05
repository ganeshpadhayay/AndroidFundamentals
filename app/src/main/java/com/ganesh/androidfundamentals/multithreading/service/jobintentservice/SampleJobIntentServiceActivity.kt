package com.ganesh.androidfundamentals.multithreading.service.jobintentservice

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.R
import kotlinx.android.synthetic.main.activity_sample_job_intent_service.*

/***
 * Helper for processing work that has been enqueued for a job/service. When running on Android O or later, the work will be dispatched as
 * a job via JobScheduler.enqueue. When running on older versions of the platform, it will use Context.startService.
 *
 * Below API 26, services can't be run in background they will be killed by the system and also app can crash if startService() gets called
 * when app is in the background!!!!!
 *
 * Both IntentService and JobIntentService are used for background processing specially over API 26 but with IntentService we get a non dismissable
 * notification and execution is guaranteed at specified time and with JobIntentService, we can get rid of the notification but then execution
 * can be deferred because it wraps the service under a job. JobIntentService saves battery by not showing notification and can be restarted
 * at a later point in time. Only drawback is these are deferrable and system can kill these when it is running on low memory!
 */
class SampleJobIntentServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_job_intent_service)
    }

    fun enqueueWork(view: View) {
        val input: String = edit_text_input?.text.toString()

        val serviceIntent: Intent = Intent(this, ExampleJobIntentService::class.java)
        serviceIntent.putExtra("inputExtra", input)

        //Here we can't set constrains like battery, wifi types like we did with JobScheduler because here the aim of JobIntentService is to
        //run the job as soon as possible
        ExampleJobIntentService.enqueueWork(this, serviceIntent)
    }
}