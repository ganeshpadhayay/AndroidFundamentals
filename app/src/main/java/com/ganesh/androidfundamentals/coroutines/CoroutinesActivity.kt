package com.ganesh.androidfundamentals.coroutines

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.R
import com.ganesh.androidfundamentals.coroutines.job.CoroutineAsAJobActivity
import com.ganesh.androidfundamentals.coroutines.parallelcoroutines.ParallelBackgroundCoroutinesActivity
import com.ganesh.androidfundamentals.coroutines.sequentialcoroutines.SequentialBackgroundCoroutinesActivity
import com.ganesh.androidfundamentals.coroutines.structuredconcurrency.StructuredConcurrencyActivity
import com.ganesh.androidfundamentals.coroutines.supervisorjob.SupervisorJobActivity
import kotlinx.android.synthetic.main.activity_coroutines.*

class CoroutinesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        button_simple_coroutine?.setOnClickListener {
            startActivity(Intent(this, SimpleCoroutineActivity::class.java))
        }

        button_coroutine_as_a_job?.setOnClickListener {
            startActivity(Intent(this, CoroutineAsAJobActivity::class.java))
        }

        button_parallel_background_calls?.setOnClickListener {
            startActivity(Intent(this, ParallelBackgroundCoroutinesActivity::class.java))
        }

        button_sequential_background_calls?.setOnClickListener {
            startActivity(Intent(this, SequentialBackgroundCoroutinesActivity::class.java))
        }

        button_structured_concurrency?.setOnClickListener {
            startActivity(Intent(this, StructuredConcurrencyActivity::class.java))
        }
        button_supervisor_job?.setOnClickListener {
            startActivity(Intent(this, SupervisorJobActivity::class.java))
        }

    }
}