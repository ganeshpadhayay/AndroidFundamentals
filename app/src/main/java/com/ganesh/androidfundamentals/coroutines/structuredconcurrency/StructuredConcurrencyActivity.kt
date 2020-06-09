package com.ganesh.androidfundamentals.coroutines.structuredconcurrency

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.NonCancellable.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

class StructuredConcurrencyActivity : AppCompatActivity() {

    /***
     * this is an exception handler which can only be attached to the parent job and not with the child jobs
     */
    private val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Exception thrown in one of the children: $exception")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_structured_concurrency)

        main()
    }

    private fun main() {

        val parentJob = CoroutineScope(IO).launch(handler) {

            //Job #A
            val jobA = launch {
                val resultA = getResult(1)
                println("resultA: $resultA")
            }
            jobA.invokeOnCompletion {
                if (it != null) {
                    println("Error getting resultA: $it")
                }
            }

            //Job #B
            val jobB = launch {
                val resultB = getResult(2)
                println("resultA: $resultB")
            }
            //this will cancel only the jobB and parent job will be a success and it is similar to throwing a CancellationException from that job
//            delay(200)
//            jobB.cancel()
            jobB.invokeOnCompletion {
                if (it != null) {
                    println("Error getting resultB: $it")
                }
            }

            //Job #C
            val jobC = launch {
                val resultC = getResult(3)
                println("resultA: $resultC")
            }
            jobC.invokeOnCompletion {
                if (it != null) {
                    println("Error getting resultC: $it")
                }
            }

        }

        parentJob.invokeOnCompletion {
            if (it != null) {
                println("Parent job failed: $it")
            } else {
                println("Parent job success")
            }
        }
    }

    private suspend fun getResult(number: Int): Int {
        delay(number * 500L)
        if (number == 2) {
//            throw Exception("Error getting result from $number")        //when throwing a regular exception, parent job fails and it fails all child jobs also
//            cancel(CancellationException("Error getting result from $number"))       //in this case, nothing happens, parent job success
            throw CancellationException("Error getting result from $number")        //works fine, does not let the parent job fail and only this job fails
        }
        return number * 2
    }
}