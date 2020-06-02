package com.ganesh.androidfundamentals.multithreading.basics

import android.os.Handler
import android.os.Looper
import android.util.Log

class ExampleLooperThread : Thread() {

    //alternate of Java's static final constant
    companion object {
        const val TAG: String = "ExampleLooperThread"
    }

    //this is handler and we will attach this to the given thread by instantiating it here and if directly crate and instance of handler and try
    //running the project, it will crash the app as looper needs to prepared and attached to the handler(java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare())
    lateinit var handler: Handler
    lateinit var looper: Looper

    override fun run() {
        //so we need to get a looper and a message queue for this background thread, this will attach a looper and tie a message queue with itself
        Looper.prepare()

        looper = Looper.myLooper()!!            //this returns the looper associated with the given thread

        handler = ExampleHandler()

        //this starts an infinite for loop and we can exit it by calling looper.quit
        Looper.loop()

//        for (i in 1..5) {
//            Log.d(TAG, "run: $i")
//            SystemClock.sleep(1000)         //it does not throw exception as it catches it internally
//        }

        Log.d(TAG, "end of run()")
    }
}