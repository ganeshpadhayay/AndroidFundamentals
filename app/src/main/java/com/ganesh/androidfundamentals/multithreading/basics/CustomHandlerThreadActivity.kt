package com.ganesh.androidfundamentals.multithreading.basics

import android.os.Bundle
import android.os.Message
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.R
import com.ganesh.androidfundamentals.multithreading.basics.ExampleHandler.Companion.TASK_A
import com.ganesh.androidfundamentals.multithreading.basics.ExampleHandler.Companion.TASK_B

class CustomHandlerThreadActivity : AppCompatActivity() {

    //alternate of Java's static final constant
    companion object {
        const val TAG: String = "CustomHandlerThreadActivity"
    }

    private var looperThread: ExampleLooperThread = ExampleLooperThread()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_handler_thread)
    }

    //if you try to start this thread again, app will crash because you cannot start a thread again, thread simply finishes its execution and terminates
    fun startThread(view: View) {
        looperThread.start()
    }

    fun stopThread(view: View) {
        looperThread.looper.quit()            //this quits the looper directly and discards the messages in the queue
        //if any message is posted to the thread after quit is called we get a warning exception(java.lang.IllegalStateException: Handler (android.os.Handler) {3d31ba5} sending message to a Handler on a dead thread)
//        looperThread?.looper?.quitSafely()            this will make sure that the messages in the queue are executed before quitting
    }

    fun taskA(view: View) {
        //if we have access to the looper of any thread, we can create its handler from outside the thread also by using this looper
//        val threadHandler: Handler = Handler(looperThread.looper)
//        threadHandler.post {
//            for (i in 1..5) {
//                Log.d(ExampleLooperThread.TAG, "run: $i")
//                SystemClock.sleep(1000)         //it does not throw exception as it catches it internally
//            }
//        }

//        looperThread?.handler?.post {
//            for (i in 1..5) {
//                Log.d(ExampleLooperThread.TAG, "run: $i")
//                SystemClock.sleep(1000)         //it does not throw exception as it catches it internally
//            }
//        }


        //In Message Queue, we can post either runnable or messages, essentially both are same but runnable is used to execute something and
        //messages are used to pass the data object also
//        var message : Message = Message()       //this gives a new fresh message instead of giving the recycled ones so we don't use this
        var message: Message = Message.obtain()
        message.what = TASK_A
        looperThread?.handler?.sendMessage(message)
    }

    fun taskB(view: View) {
        var message: Message = Message.obtain()
        message.what = TASK_B
        looperThread?.handler?.sendMessage(message)
    }
}

/***
These anonymous classes(Runnables we have been creating) are just like not static inner classes which always have the reference to
outer class objects and can create potential memory leaks, so better to use static class with weak references
 **/