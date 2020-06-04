package com.ganesh.androidfundamentals.multithreading.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.ganesh.androidfundamentals.App.Companion.CHANNEL_ID
import com.ganesh.androidfundamentals.R
import com.ganesh.androidfundamentals.main.MainActivity

/***
 * Since we have extended Service class only, all the code under onStartCommand() will run on the main thread so either start a new thread in
 * onStartCommand() or extend to IntentService
 *
 *
 * so, it seems we have two places where we put startForeground(notification) and startForegroundService(serviceIntent), out of these two
 * startForeground(notification) is mandatory for foreground services(services above API 26)
 */
class ExampleService : Service() {

    /***
     * Although this method is mandatory but we only need this for bound services and not for background or foreground services
     * Bound Services are the services where other components can bind to it and communicate with it as a server-client mechanism.
     */
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    /***
     * it will be called only once when our service is created
     */
    override fun onCreate() {
        super.onCreate()
    }

    /***
     * this gets triggered when we start our service(background and foreground both), it gets called every time we call startService() method
     * we can call startService() with different intent every time and we can make the same service execute different tasks for us.
     *
     * do heavy work in background thread
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        return super.onStartCommand(intent, flags, startId)       //delete this as we want to return something else
        val input: String? = intent?.getStringExtra("inputExtra")

        //class we want to open when clicked on notification
        var notificationIntent: Intent = Intent(this, MainActivity::class.java)

        //pending intent is used to defer an intent in notification or alarm manager
        var pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        //this makes sure that above Oreo(API 26) this would run through a channel but below API 26 it will handle this without any issue
        var notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Example Service")
            .setSmallIcon(R.drawable.ic_android)
            .setContentIntent(pendingIntent)
            .build()

        //if you comment this, you service will run like a normal background service and will get killed if in the background after API 26
        startForeground(1, notification)

        //service can be stopped from here also as it needs to be stopped form either here or from where you have invoked the service
//        stopSelf()   //it should be called when your service has finished all the work

        return START_NOT_STICKY             //NOT_STICKY means it will not start the service again when app is killed and opened again, STICKY means open with null intent, REDELIVER_INTENT means start with the same intent
    }

    /***
     * when services is finished onDestroy() gets called
     */
    override fun onDestroy() {
        super.onDestroy()
    }

}