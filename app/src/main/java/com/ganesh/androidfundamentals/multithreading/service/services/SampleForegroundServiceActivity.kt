package com.ganesh.androidfundamentals.multithreading.service.services

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.R
import kotlinx.android.synthetic.main.activity_sample_foreground_service.*

/***
 * After API 26(Oreo), we can't run a service in background if the app goes in background or gets killed so either we can use JobScheduler or
 * we can change these services to foreground services
 *
 * Also, for foreground services we need to show a non-dismissable notification to the user and since API 26, notification can be shown only
 * through Notification channels
 */
class SampleForegroundServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_foreground_service)
    }

    fun startService(view: View) {
        val input: String = edit_text_input.text.toString()
        val serviceIntent: Intent = Intent(this, ExampleService::class.java)
        serviceIntent.putExtra("inputExtra", input)
        //here if this startService() method gets called from background it will crash the app so we must use startForegroundService() and
        //if after 5 seconds of calling startForegroundService() form here, we do not call startForeground(notification) from service class
        //system will kill the service
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }

        //or we can do this also, this does the same thing internally
//        ContextCompat.startForegroundService(this, serviceIntent)
    }

    fun stopService(view: View) {
        val serviceIntent: Intent = Intent(this, ExampleService::class.java)
        stopService(serviceIntent)
    }
}