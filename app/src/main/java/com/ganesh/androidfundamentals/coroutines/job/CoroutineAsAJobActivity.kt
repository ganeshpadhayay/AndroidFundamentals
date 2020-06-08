package com.ganesh.androidfundamentals.coroutines.job

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.R
import kotlinx.android.synthetic.main.activity_coroutine_as_a_job.*
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job

class CoroutineAsAJobActivity : AppCompatActivity() {

    private val PROGRESS_MAX = 100
    private val PROGRESS_START = 0
    private val JOB_TIME = 4000

    //it is a subclass of job and has some extra methods like complete() and completeExceptionally(), these mark the job as complete
    //using this we can handle when we want to mark the job complete and not let the coroutine mark it complete to handle cancellation and
    //timeout scenarios.
    private lateinit var job: CompletableJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_as_a_job)

        job_button?.setOnClickListener {

        }
    }

    fun initJob(){
        job_button?.text = "Start Job #1"
        job_complete_text?.text = ""
        job = Job()
    }
}