package com.ganesh.androidfundamentals.multithreading.service.intentservices

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ganesh.androidfundamentals.R
import com.ganesh.androidfundamentals.multithreading.service.services.ExampleService
import kotlinx.android.synthetic.main.activity_sample_foreground_service.*

/***
 * IntentService is a base class for Services that handle asynchronous requests (expressed as Intents) on demand.
 * Clients send requests through Context.startService(Intent) calls; the service is started as needed, handles each Intent in turn using
 * a worker thread, and stops itself when it runs out of work.
 *
 * Also we do need to handle the killing of this service as intent service does ghat for us, it terminated when it finishes the execution

This "work queue processor" pattern is commonly used to offload tasks from an application's main thread. The IntentService class exists to
simplify this pattern and take care of the mechanics. To use it, extend IntentService and implement onHandleIntent(android.content.Intent).
IntentService will receive the Intents, launch a worker thread, and stop the service as appropriate.

All requests are handled on a single worker thread -- they may take as long as necessary (and will not block the application's main loop),
but only one request will be processed at a time.
 */
class SampleIntentServiceActivity : AppCompatActivity() {

    //alternate of Java's static final constant
    companion object {
        const val TAG: String = "SampleIntentServiceActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_intent_service)
    }

    fun startIntentService(view: View) {
        val input: String = edit_text_input.text.toString()
        val serviceIntent: Intent = Intent(this, ExampleIntentService::class.java)
        serviceIntent.putExtra("inputExtra", input)
        ContextCompat.startForegroundService(this, serviceIntent)
    }
}


//so, we can either use JobIntentService for API 26 above but the problem with the job is it can be deferred but a service(intentService) is started
//immediately so we can use intent service for both API level less than 26 and above also using startForegroundService()