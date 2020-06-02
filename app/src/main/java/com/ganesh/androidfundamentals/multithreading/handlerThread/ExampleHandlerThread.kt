package com.ganesh.androidfundamentals.multithreading.handlerThread

import android.os.*
import android.util.Log

class ExampleHandlerThread :
    HandlerThread("ExampleHandlerThread", Process.THREAD_PRIORITY_BACKGROUND) {

    companion object {
        private const val TAG = "ExampleHandlerThread"
        const val EXAMPLE_TASK_1: Int = 1
        const val EXAMPLE_TASK_NEW: Int = -1
    }

    private lateinit var handler: Handler

    //since this callback is  given on the background thread which means our handler is instantiated on the bg thread
    //this callback is delivered from the run() method definition of HandlerThread after the looper.prepare() method and before the looper.loop() method
    override fun onLooperPrepared() {
        handler = ExampleHandler()
    }

    fun getHandler(): Handler {
        return handler
    }

    //this will not leak memory as it a static inner class and does not store any reference to outer class
    class ExampleHandler : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                EXAMPLE_TASK_1 -> {
                    Log.d(TAG, "Example Task, arg1: " + msg.arg1 + " obj: " + msg.obj)
                    for (i in 1..5) {
                        Log.d(TAG, "handleMessage: $i")
                        SystemClock.sleep(1000)
                    }
                }
                EXAMPLE_TASK_NEW -> {
                    Log.d(TAG, "Example Task, arg1: " + msg.arg1 + " obj: " + msg.obj)
                    Log.d(TAG, "handling send to target message")
                }
            }
        }
    }
}