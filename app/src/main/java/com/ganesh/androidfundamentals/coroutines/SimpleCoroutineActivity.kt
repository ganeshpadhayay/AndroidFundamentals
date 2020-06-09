package com.ganesh.androidfundamentals.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.R
import kotlinx.android.synthetic.main.activity_simple_coroutine.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.random.Random

/***
 * Basic difference between the thread and a coroutine is that coroutines are light weight thread and do not require context switching and multiple
 * coroutines can run in a single thread. Imagine coroutines like a job and multiple jobs are executing on a single thread
 *
 * Coroutine Scopes is basically grouping the relevant coroutines together so that they can be cancelled collectively
 */
class SimpleCoroutineActivity : AppCompatActivity() {

    private val RESULT_1 = "Result #1"
    private val RESULT_2 = "Result #2"
    private val JOB_TIMEOUT = 2100L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_coroutine)

        button.setOnClickListener {
            //we will launch coroutines from here using IO CoroutineScope as IO is used for network or DB operation, Main is for main thread
            //Default is for heavy computation on the background thread like filtering large lists
            //launch() is a coroutine builder which starts the coroutine
            CoroutineScope(IO).launch {
//                fakeApiRequest()

                //this is to test the timeout cases
                fakeApiRequestForTimeoutCases()
            }
        }
    }

    /***
     * this is to explore the timeout cases
     */
    private suspend fun fakeApiRequestForTimeoutCases() {
        withContext(IO) {
            val job = withTimeoutOrNull(JOB_TIMEOUT) {
                val result1 = getResult1FromApi()   //wait
                updateTextOnMainThread("Got $result1")

                val result2 = getResult2FromApi(result1)    //wait
                updateTextOnMainThread("Got $result2")
            }   //wait

            //job will be null if it timed out
            if (job == null) {
                val cancelMessage: String = "Cancelling Job... Job took longer than $JOB_TIMEOUT"
                updateTextOnMainThread(cancelMessage)
            }
        }

    }

    /***
     * this method will also have to be a suspend function as it is calling a suspend function or it could start a coroutine inside it and
     * then call the method getResult1FromApi()
     *
     * suspend functions are called suspend functions because they can suspend the execution for example here, this function will stop at
     * first line till it gets the result from another suspend function getResult1FromApi() and after getting the response only then it will
     * execute the next lines, so in a way we are making asynchronous call in a sequential(synchronous) manner!!
     */
    private suspend fun fakeApiRequest() {
        val result1 = getResult1FromApi()
        println("debug: $result1")
//        text?.text = result1            //this will crash the app as we are trying to access the view from background thread
        updateTextOnMainThread(result1)

        val result2 = getResult2FromApi(result1)
        println("debug: $result2")
        updateTextOnMainThread(result2)
    }

    /***
     * this is also a suspend function for main thread operation but this will be called from a coroutine which has the scope of main thread
     */
    private suspend fun updateTextOnMainThread(input: String) {
        //this will run it on the main thread, it switches the context from one thread to another thread, here it is changing the context from
        //IO to Main.
        withContext(Main) {
            updateTextView(input)
        }

        //alternatively, we could have done this also
//        CoroutineScope(Main).launch {
//            updateTextView(input)
//        }
    }

    private fun updateTextView(input: String) {
        val newText: String = text?.text.toString() + "\n$input";
        text?.text = newText
    }

    /***
     * only suspend functions or coroutines can call these functions, does not mean that these run on main or background thread, it is the
     * coroutine which runs in background and coroutines can call only suspend functions
     *
     * Example of such suspend functions could be DAO methods used for DB operations and retrofit's call() methods for network operation
     */
    private suspend fun getResult1FromApi(): String {
        logThread("getResult1FromApi")
        delay(1000)     //looks similar to Thread.sleep() but it is different as this will delay this single coroutine and not the thread
//        Thread.sleep(1000)  //this will delay the whole thread and all the coroutines associated with this thread
        return RESULT_1;
    }

    private suspend fun getResult2FromApi(result1: String): String {
        logThread("getResult2FromApi")
        delay(1000)
        return RESULT_2 + result1
    }

    /***
     * this method is basically used to verify that which method is running on which thread
     */
    private fun logThread(methodName: String) {
        println("debug: ${methodName}: ${Thread.currentThread().name}")
    }


    private fun main() {
        CoroutineScope(Main).launch {
            println("Current Thread: ${Thread.currentThread().name}")
            //if calling function freezeMainThread() one time or maybe 5-10 times it won't freeze UI as coroutines are small part of thread
            //but if you call like 1000 coroutines then main thread will be occupied and might freeze the UI
            for (i in 1..100000) {
                freezeMainThread()
            }
        }
    }

    private suspend fun freezeMainThread() {
        println("Starting network request")
        delay(3000)
        println("Finished network request")
    }

    /***
     * runBlocking is basically a coroutine scope only difference is that this blocks the thread(Yes, complete thread) till this blocks execution
     * both of these jobs will start at the same time but then runBlocking() will block the thread for some time and after that time
     * job1 will be resumed.
     */
    private fun runBlockingDemonstration() {
        //Job #1
        val job1 = CoroutineScope(Main).launch {
            println("Starting job in thread: ${Thread.currentThread().name}")

            val result1 = getResult()
            println("result1: $result1")

            val result2 = getResult()
            println("result1: $result2")

            val result3 = getResult()
            println("result1: $result3")

            val result4 = getResult()
            println("result1: $result4")

            val result5 = getResult()
            println("result1: $result5")
        }

        //job #2
        CoroutineScope(Main).launch {
            delay(1000)
            runBlocking {
                println("Blocking thread: ${Thread.currentThread().name}")
                delay(4000)
                println("Done blocking thread: ${Thread.currentThread().name}")
            }
        }

    }

    private suspend fun getResult(): Int {
        delay(1000)
        return Random.nextInt(0, 100)
    }
}

/***
Do not use Global Scope as they do not sync well with the parent job and cancellation of parent job does not ensure
the cancellation of child jobs
This can be useful when we have an independent job running on GlobalScope but when it comes to Parent-child job context, do not
ever use GlabalScope */