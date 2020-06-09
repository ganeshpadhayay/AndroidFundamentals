package com.ganesh.androidfundamentals.coroutines.supervisorjob

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.R
import kotlinx.coroutines.*

class SupervisorJobActivity : AppCompatActivity() {

    /***
     * this is an exception handler which can only be attached to the parent job and not with the child jobs
     */
    private val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Exception thrown in one of the children: $exception")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supervisor_job)
        main()
    }

    private fun main() {

        val parentJob = CoroutineScope(Dispatchers.IO).launch(handler) {

            /***
             * while using supervisor scope we can pass the exception handler to either parent job or the child job which could throw an exception
             * and it will be handled correctly
             */
            supervisorScope {
                //Job #A
                val jobA = launch {
                    val resultA = getResult(1)
                    println("resultA: $resultA")
                }

                //Job #B
                val jobB = launch(handler) {
                    val resultB = getResult(2)
                    println("resultA: $resultB")
                }

                //Job #C
                val jobC = launch {
                    val resultC = getResult(3)
                    println("resultA: $resultC")
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
            throw Exception("Error getting result from $number")     //when throwing a regular exception, parent job fails and it fails all child jobs also
        }
        return number * 2
    }
}