package com.ganesh.androidfundamentals.multithreading

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.R
import com.ganesh.androidfundamentals.multithreading.asynctask.SampleAsyncTaskActivity
import com.ganesh.androidfundamentals.multithreading.customHandlerThread.CustomHandlerThreadActivity
import com.ganesh.androidfundamentals.multithreading.handlerThread.SampleHandlerThreadActivity
import com.ganesh.androidfundamentals.multithreading.service.intentservices.SampleIntentServiceActivity
import com.ganesh.androidfundamentals.multithreading.service.services.SampleForegroundServiceActivity
import com.ganesh.androidfundamentals.multithreading.service.jobscheduler.SampleJobSchedulerActivity
import kotlinx.android.synthetic.main.activity_multi_threading.*


class MultiThreadingActivity : AppCompatActivity() {

    //alternate of Java's static final constant
    companion object {
        const val TAG: String = "MultiThreadingActivity"
    }

    //handler is associated with the Thread from where it is initialized so here this Handler is main thread's handler, a thread can have multiple handlers
    //Also, normally a thread is created, it starts its execution and then terminates but main thread is a special thread which has its own
    //looper and message queue and an infinite for loop ensures that main thread does not finish unless user exits the app, looper loops and excutes
    //the runnable tasks on main thread.
    //It is handler's responsibility to put tasks in message queue and take them out for their execution this means that any handler of a thread
    //has the instance of message queue with it.
    //We can make our custom looper, message queue and handler for any Java Thread.
    private val mainHandler = Handler()

    @Volatile
    private var stopThread: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_threading)
    }

    fun startThread(view: View) {
        stopThread = false
        //this code will run on the main thread and block UI and ANR will be observed
//        for (i in 1..10) {
//            Log.d(TAG, "startThread: $i")
//            Thread.sleep(1000)          //why is this not throwing exception in Koltin, same line in Java throws exception
//        }

        //this will create a thread by extending a Thread class and run the same piece of code in a background thread
//        val exampleThread = ExampleThread(10)
//        exampleThread.start()

        //this will create a thread by implementing a runnable interface and run the same piece of code in a background thread
        var exampleRunnable = ExampleRunnable(10, mainHandler)
//        exampleRunnable.run()               //this will run this runnable on the main thread, if this is requirement
        Thread(exampleRunnable).start()

        //we can also use anonymous runnable class to start a thread
//        Thread(Runnable {
//            //work
//        }).start()
    }


    fun stopThread(view: View) {
        stopThread = true
    }

    //In Kotlin, we have two types of nested classes, Nested classes which don't have access to outer class varibles
    class ExampleThread(private var seconds: Int) : Thread() {

        override fun run() {
            for (i in 1..seconds) {
                Log.d(TAG, "startThread: $i")
                sleep(1000)
            }
        }
    }


    //Inner class which can access the outer class variables
    inner class ExampleRunnable(private var seconds: Int, private var mainHandler: Handler) :
        Runnable {

        override fun run() {
            for (i in 1..seconds) {
                if (stopThread) {
                    return
                }
                //when we are at 5 seconds, we want to update something in the UI thread
                if (i == 5) {
//                    button_start_thread?.text = "50%"        this would crash the app as you can't touch views from a background thread
//                    mainHandler.post {
//                        button_start_thread?.text = "50%"
//                    }
                    //this is the handler associated with this background thread(runnable) and since it does not have any looper, it will crash
                    //    java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()
//                    val threadHandler = Handler()
//                    threadHandler.post {
//                        button_start_thread?.text = "50%"
//                    }

                    //But, we give it a looper(from any thread, handler will act like the handler for that thread), here mainLooper
//                    val threadHandler = Handler(Looper.getMainLooper())
//                    threadHandler.post {
//                        button_start_thread?.text = "50%"
//                    }

                    //Alternatively, we have a post method in our view class also which can directly execute code on main thread
//                    button_start_thread?.post {
//                        Runnable {
//                            button_start_thread?.text = "50%"
//                        }
//                    }

                    //this is an activity method, can be called directly and executes the runnable on main thread
                    runOnUiThread(Runnable {
                        button_start_thread?.text = "50%"
                    })
                }
                Log.d(TAG, "startThread: $i")
                Thread.sleep(1000)
            }
        }


    }

    fun openCustomHandlerThread(view: View) {
        startActivity(Intent(this, CustomHandlerThreadActivity::class.java))
    }

    fun openSampleHandlerThreadActivity(view: View) {
        startActivity(Intent(this, SampleHandlerThreadActivity::class.java))
    }

    fun openAsyncTaskActivity(view: View) {
        startActivity(Intent(this, SampleAsyncTaskActivity::class.java))
    }

    fun openSampleServiceActivity(view: View) {
        startActivity(Intent(this, SampleForegroundServiceActivity::class.java))
    }

    fun openSampleJobSchedulerActivity(view: View) {
        startActivity(Intent(this, SampleJobSchedulerActivity::class.java))
    }

    fun openSampleIntentServiceActivity(view: View) {
        startActivity(Intent(this, SampleIntentServiceActivity::class.java))
    }
}

// We can create Threads either by extending Thread Class or creating a runnable by implementing Runnable interface
//In Java, we communicate between threads using wait(), notify(), and notifyAll() methods but in Android we have Handler and Looper for these
//tasks and these also act as a basic units for AsyncTask, HandlerThread, ThreadPoolExecutor, etc.
