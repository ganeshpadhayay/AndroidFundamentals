package com.ganesh.androidfundamentals.coroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ganesh.androidfundamentals.R
import com.ganesh.androidfundamentals.coroutines.job.CoroutineAsAJobActivity
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
    }
}