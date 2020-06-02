package com.ganesh.androidfundamentals.multithreading.customHandlerThread

import android.os.Handler
import android.os.Message
import android.util.Log

/***
 * we can have multiple handlers for a thread but only one looper and message queue and even if we have multiple handlers their messages/posts
 * will be run in a sequential manner
 */
class ExampleHandler : Handler() {

    //alternate of Java's static final constant
    companion object {
        const val TAG: String = "ExampleHandler"
        const val TASK_A: Int = 1
        const val TASK_B: Int = 2
    }


    override fun handleMessage(msg: Message) {
        when (msg.what) {
            TASK_A -> {
                Log.d(TAG, "Task A Executed")
            }
            TASK_B -> {
                Log.d(TAG, "Task B Executed")
            }
        }
    }
}