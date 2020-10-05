package com.ganesh.androidfundamentals.multithreading.service.intentservices

import android.app.IntentService
import android.app.Notification
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.PowerManager
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import com.ganesh.androidfundamentals.MyApplication.Companion.CHANNEL_ID
import com.ganesh.androidfundamentals.R

/***
 * As you can notice, here we do not need to override onBind() method as IntentService does that for us
 * Also, no need to handle onStartCommand() method here
 */
class ExampleIntentService : IntentService("ExampleIntentService") {

    //alternate of Java's static final constant
    companion object {
        private const val TAG: String = "ExampleIntentService"
    }

    private lateinit var wakeLock: PowerManager.WakeLock

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")

        //acquire wake lock
        val powerManager: PowerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "ExampleApp:WakeLock")
        wakeLock.acquire(60 * 1000)
        Log.d(TAG, "wakelock acquired")

        //create notification one time here
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example IntentService")
                .setContentText("Running...")
                .setSmallIcon(R.drawable.ic_android)
                .build()

            //this will make sure that the service is a foreground service
            startForeground(1, notification)
        }

    }

    //this is the substitute of return type(STICKY, NON_STICKY from services)
    init {
        setIntentRedelivery(false)
    }

    //this method gets executed on the background thread
    //intents will be passed sequentially if needs to run those intents in parallel we can extend from normal service and spawn multiple threads
    //for each intent
    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent")

        val input: String? = intent?.getStringExtra("inputExtra")

        for (i in 1..10) {
            Log.d(TAG, "$input - $i")
            SystemClock.sleep(1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        wakeLock.release()
        Log.d(TAG, "wakelock released")
    }

}