package com.ganesh.androidfundamentals.multithreading.service.intentservices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ganesh.androidfundamentals.R

class SampleIntentServiceActivity : AppCompatActivity() {

    //alternate of Java's static final constant
    companion object {
        const val TAG: String = "MultiThreadingActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_intent_service)
    }
}