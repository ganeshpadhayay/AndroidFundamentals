package com.ganesh.androidfundamentals.workmanager.backgroundwork

import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(var context: Context, params: WorkerParameters) : Worker(context, params) {

    private lateinit var networkChangeReceiver: NetworkChangeReceiver

    override fun doWork(): Result {
        Log.d("Ganesh", "doWork MyWorker")

//        registerReceiver()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            Log.d("Ganesh", "Testing delayed 5 second runnable")
            Toast.makeText(context.applicationContext, "Testing delayed 5 second runnable", Toast.LENGTH_SHORT).show()
        }, 2000)

//        val receiverHandler = Handler(Looper.getMainLooper())
//        receiverHandler.postDelayed({
//            Log.d("Ganesh", "trying to unregister from main thread")
//            Toast.makeText(context, "trying to unregister from main thread", Toast.LENGTH_SHORT).show()
//            unregisterReceiver()
//        }, 5000)

        return Result.success()
    }

    private fun unregisterReceiver() {
        networkChangeReceiver?.let {
            context.applicationContext.unregisterReceiver(it)
        }
        Log.d("Ganesh", "unregistering receiver")
    }

    private fun registerReceiver() {
        networkChangeReceiver = NetworkChangeReceiver()
        context.applicationContext.registerReceiver(
            networkChangeReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        Log.d("Ganesh", "registering receiver")
    }

    @SuppressLint("LogNotTimber")
    override fun onStopped() {
        Log.d("Ganesh", "onStopped MyWorker")
        super.onStopped()
    }

}