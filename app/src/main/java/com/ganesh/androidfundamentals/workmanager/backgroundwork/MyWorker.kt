package com.ganesh.androidfundamentals.workmanager.backgroundwork

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(var context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        Log.d("Ganesh", "doWork MyWorker")
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            Toast.makeText(context, "Testing", Toast.LENGTH_SHORT).show()
        }, 0)

        return Result.success()
    }

    @SuppressLint("LogNotTimber")
    override fun onStopped() {
        Log.d("Ganesh", "onStopped MyWorker")
        super.onStopped()
    }
}