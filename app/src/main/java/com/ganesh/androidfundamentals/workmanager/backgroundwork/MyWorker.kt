package com.ganesh.androidfundamentals.workmanager.backgroundwork

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(var context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {
        private const val TAG = "Ganesh"
        const val REGISTER = 1
        const val UNREGISTER = -1
    }

    //handler for the background thread running in doWork() method
    lateinit var handler: Handler

    @SuppressLint("LogNotTimber")
    override fun doWork(): Result {
        Log.d(TAG, "doWork: in my Worker from ${Thread.currentThread().name}")

        Looper.prepare()
        handler = BackgroundHandler(context)

        //send message to register to receivers
        val registerMessage: Message = Message.obtain()
        registerMessage.what = REGISTER
        handler.sendMessageDelayed(registerMessage, 0)

        //send message to unregister the receivers
        val unregisterMessage: Message = Message.obtain()
        unregisterMessage.what = UNREGISTER
        handler.sendMessageDelayed(unregisterMessage, 10000)

        Looper.loop()
        Log.d(TAG, "doWork: returning result from ${Thread.currentThread().name}")
        return Result.success()
    }

    @SuppressLint("LogNotTimber")
    override fun onStopped() {
        Log.d("Ganesh", "onStopped MyWorker")
        super.onStopped()
    }

}