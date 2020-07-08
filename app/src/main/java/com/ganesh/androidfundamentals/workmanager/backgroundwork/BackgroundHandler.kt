package com.ganesh.androidfundamentals.workmanager.backgroundwork

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Message
import android.util.Log
import com.ganesh.androidfundamentals.workmanager.backgroundwork.MyWorker.Companion.REGISTER
import com.ganesh.androidfundamentals.workmanager.backgroundwork.MyWorker.Companion.UNREGISTER

class BackgroundHandler(var context: Context) : Handler() {

    companion object {
        private const val TAG = "Ganesh"
    }

    private var networkChangeReceiver: NetworkChangeReceiver = NetworkChangeReceiver()
    private var userPresentBroadcastReceiver: UserPresentBroadcastReceiver = UserPresentBroadcastReceiver()
    private var airplaneReceiver: AirplaneModeReceiver = AirplaneModeReceiver()

    override fun handleMessage(msg: Message) {
        when (msg.what) {
            REGISTER -> {
                registerReceiver()
            }
            UNREGISTER -> {
                unregisterReceiver()
            }
        }
    }

    @SuppressLint("LogNotTimber")
    private fun registerReceiver() {
        context.applicationContext?.registerReceiver(networkChangeReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        context.applicationContext?.registerReceiver(userPresentBroadcastReceiver, IntentFilter(IntentFilter(Intent.ACTION_USER_PRESENT)))
        context.applicationContext?.registerReceiver(airplaneReceiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        Log.d(TAG, "registering receiver from ${Thread.currentThread().name}")
    }

    @SuppressLint("LogNotTimber")
    private fun unregisterReceiver() {
        networkChangeReceiver?.let { context.applicationContext.unregisterReceiver(it) }
        userPresentBroadcastReceiver?.let { context.applicationContext.unregisterReceiver(it) }
        airplaneReceiver?.let { context.applicationContext.unregisterReceiver(it) }
        looper.quit()
        Log.d(TAG, "unregistering receiver from ${Thread.currentThread().name}")
    }
}
