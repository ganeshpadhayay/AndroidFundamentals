package com.ganesh.androidfundamentals.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.R
import kotlinx.android.synthetic.main.activity_sample_coroutine.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/***
 * Basic difference between the thread and a coroutine is that coroutines are light weight thread and do not require context switching and multiple
 * coroutines can run in a single thread. Imagine coroutines like a job and multiple jobs are executing on a single thread
 *
 * Coroutine Scopes is basically grouping the relevant coroutines together so that they can be cancelled collectively
 */
class SampleCoroutineActivity : AppCompatActivity() {

    private val RESULT_1 = "Result #1"
    private val RESULT_2 = "Result #2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_coroutine)

        button.setOnClickListener {
            //we will launch coroutines from here using IO CoroutineScope as IO is used for network or DB operation, Main is for main thread
            //Default is for heavy computation on the background thread like filtering large lists
            //launch() is a coroutine builder which starts the coroutine
            CoroutineScope(IO).launch {
                fakeApiRequest()
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
}