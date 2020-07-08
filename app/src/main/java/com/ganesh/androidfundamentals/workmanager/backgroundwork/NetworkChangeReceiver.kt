package com.ganesh.androidfundamentals.workmanager.backgroundwork

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NetworkChangeReceiver : BroadcastReceiver() {
    @SuppressLint("LogNotTimber")
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("Ganesh", "onReceive NetworkChangeReceiver")
    }
}