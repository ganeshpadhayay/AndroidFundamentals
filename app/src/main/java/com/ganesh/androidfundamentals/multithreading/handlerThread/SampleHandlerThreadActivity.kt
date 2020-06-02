package com.ganesh.androidfundamentals.multithreading.handlerThread

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Message
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.R
import com.ganesh.androidfundamentals.multithreading.handlerThread.ExampleHandlerThread.Companion.EXAMPLE_TASK_1
import com.ganesh.androidfundamentals.multithreading.handlerThread.ExampleHandlerThread.Companion.EXAMPLE_TASK_NEW

class SampleHandlerThreadActivity : AppCompatActivity() {

    //this will have a looper and a message queue along with a background thread but handlers need to be created
    private var handlerThread: ExampleHandlerThread = ExampleHandlerThread()

    //no need of this when we have created our own handlerThread which has a handler in it.
//    private lateinit var threadHandler: Handler

    //alternate of Java's static final constant
    companion object {
        const val TAG: String = "SampleHandlerThreadActivity"
    }

    private val token = Object()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_handler_thread)
        //as per normal Java rules, we can start a thread instance only once otherwise it will crash the app
        handlerThread.start()

        //we can instantiate out handler here as we already have a prepared looper with us
//        threadHandler = Handler(handlerThread.looper)
    }

    override fun onDestroy() {
        super.onDestroy()
        handlerThread.quit()
    }

    fun doWork(view: View) {
        //Messages
        var message: Message = Message.obtain()
        message.what = EXAMPLE_TASK_1
        message.arg1 = 23
        message.obj = "String Object"
//        message.data = Bundle()
        handlerThread?.getHandler()?.sendMessage(message)
//        handlerThread?.getHandler()?.sendEmptyMessage(1)      //does the same thing under the hood only for what field

        //we have some variants of this also where we can pass the hanlder in the obtain() method
        var newMessage: Message = Message.obtain(handlerThread?.getHandler())
        newMessage.what = EXAMPLE_TASK_NEW
        newMessage.sendToTarget()

//        threadHandler.postDelayed(ExampleRunnable1(), 2000)
//        threadHandler.post(ExampleRunnable2())
//        threadHandler.postAtFrontOfQueue(ExampleRunnable2())

        //using our HandlerThread
//        handlerThread?.getHandler()?.post(ExampleRunnable1())
//        handlerThread?.getHandler()?.post(ExampleRunnable2())
//        handlerThread?.getHandler()?.postAtTime(ExampleRunnable2(), token, SystemClock.uptimeMillis())
    }

    //handlers can only remove the messages they have put in the message queue and they can't remove the messages put in the message queue by
    //other handlers.
    fun removeMessages(view: View) {
//        handlerThread?.getHandler()?.removeCallbacksAndMessages(null)       //when we pass null, it will remove all the messages and runnables
        handlerThread?.getHandler()?.removeMessages(EXAMPLE_TASK_NEW)
//        handlerThread?.getHandler()?.removeCallbacks(ExampleRunnable2(), token)     //we can use token to remove any particular instance of runnable
    }

    //by default in kotlin if a nested class in not inner then it is static of Java
    class ExampleRunnable1 : Runnable {
        @SuppressLint("LongLogTag")
        override fun run() {
            for (i in 1..5) {
                Log.d(TAG, "Runnable1 : $i")
                SystemClock.sleep(1000)
            }
        }

    }

    class ExampleRunnable2 : Runnable {
        @SuppressLint("LongLogTag")
        override fun run() {
            for (i in 1..5) {
                Log.d(TAG, "Runnable2 : $i")
                SystemClock.sleep(1000)
            }
        }

    }
}