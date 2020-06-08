package com.ganesh.androidfundamentals.coroutines.parallelcoroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.R
import kotlinx.android.synthetic.main.activity_simple_coroutine.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlin.system.measureTimeMillis

class ParallelBackgroundCoroutinesActivity : AppCompatActivity() {

    private val RESULT_1 = "Result #1"
    private val RESULT_2 = "Result #2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parallel_background_coroutines)

        button?.setOnClickListener {
            setNewText("Clicked!")
//            fakeApiRequestUsingLaunch()
            fakeApiRequestUsingAsyncAndAwait()
        }
    }

    //ASYNC AND AWAIT
    private fun fakeApiRequestUsingAsyncAndAwait() {
        CoroutineScope(IO).launch {
            val executionTime = measureTimeMillis {
                val result1: Deferred<String> = async {
                    println("debug: launching job1 in thread: ${Thread.currentThread().name}")
                    getResult1FromApi()
                }

                val result2: Deferred<String> = async {
                    println("debug: launching job2 in thread: ${Thread.currentThread().name}")
                    getResult2FromApi()
                }

                setTextOnMainThread("Got ${result1.await()}")
                setTextOnMainThread("Got ${result2.await()}")

                //same thing with launch and join
//                var result: String = ""
//                val job = launch {
//                    result = getResult1FromApi()
//                }
//                job.join()
//                setTextOnMainThread(result)
            }
            println("debug: total elapsed time: $executionTime ms")
        }
    }

    //LAUNCH AND JOIN(if result is to be published)
    private fun fakeApiRequestUsingLaunch() {
        val startTime = System.currentTimeMillis()
        //let us define a parent-child job relationship here
        val parentJob = CoroutineScope(IO).launch {
            //these two jobs will run in parallel and start at the same time
            val job1 = launch {
                val time1 = measureTimeMillis {
                    println("debug: launching job1 in thread: ${Thread.currentThread().name}")
                    val result1 = getResult1FromApi()
                    setTextOnMainThread("Got $result1")
                }
                println("debug: job1 completed in $time1 ms")
            }
//            job1.join()         //this will stop the execution till job1 is completed

            val job2 = launch {
                val time2 = measureTimeMillis {
                    println("debug: launching job1 in thread: ${Thread.currentThread().name}")
                    val result2 = getResult2FromApi()
                    setTextOnMainThread("Got $result2")
                }
                println("debug: job2 completed in $time2 ms")
            }
        }
        parentJob.invokeOnCompletion {
            println("debug: total elapsed time: ${System.currentTimeMillis() - startTime} ms")
        }
    }


    private suspend fun getResult1FromApi(): String {
        delay(1000)
        return RESULT_1;
    }

    private suspend fun getResult2FromApi(): String {
        delay(1700)
        return RESULT_2
    }

    private suspend fun setTextOnMainThread(input: String) {
        withContext(Dispatchers.Main) {
            setNewText(input)
        }
    }

    private fun setNewText(input: String) {
        val newText: String = text?.text.toString() + "\n$input";
        text?.text = newText
    }
}