package com.ganesh.androidfundamentals.coroutines.sequentialcoroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.R
import kotlinx.android.synthetic.main.activity_simple_coroutine.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlin.system.measureTimeMillis

class SequentialBackgroundCoroutinesActivity : AppCompatActivity() {

    private val RESULT_1 = "Result #1"
    private val RESULT_2 = "Result #2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sequential_background_coroutines)

        button?.setOnClickListener {
            setNewText("Clicked!")
            fakeApiRequest()
        }
    }

    private fun fakeApiRequest() {
        CoroutineScope(IO).launch {
            val executionTime = measureTimeMillis {
                val result1 = async {
                    println("debug: launching job1 in thread: ${Thread.currentThread().name}")
                    getResult1FromApi()
                }.await()
                setTextOnMainThread("Got $result1")

                val result2 = async {
                    println("debug: launching job2 in thread: ${Thread.currentThread().name}")
                    //let us try to catch the cancellation exception
                    try {
//                        getResult2FromApi("sfdfgegfdg")
                        getResult2FromApi(result1)
                    } catch (e: CancellationException) {
                        e.message
                    }
                }.await()
                setTextOnMainThread("Got $result2")
            }
            println("debug: total elapsed time: $executionTime ms")
        }
    }


    private suspend fun getResult1FromApi(): String {
        delay(1000)
        return RESULT_1;
    }

    private suspend fun getResult2FromApi(result: String): String {
        delay(1700)
        if (result.equals(RESULT_1, ignoreCase = true)) {
            return RESULT_2
        }
        throw CancellationException("Result #1 was incorrect...")
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