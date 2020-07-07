package com.ganesh.androidfundamentals.workmanager.backgroundwork

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class MyCoroutineWorker(var context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    companion object {
        private const val TAG = "Ganesh"
    }

    @SuppressLint("LogNotTimber")
    override suspend fun doWork(): Result {
        Log.d(TAG, "doWork: in my Worker from ${Thread.currentThread().name}")

        Log.d(TAG, "doWork: returning result from ${Thread.currentThread().name}")
        return Result.success()
    }
}