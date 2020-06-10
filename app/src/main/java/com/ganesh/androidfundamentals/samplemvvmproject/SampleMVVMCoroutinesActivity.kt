package com.ganesh.androidfundamentals.samplemvvmproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ganesh.androidfundamentals.R
import com.ganesh.androidfundamentals.samplemvvmproject.viewmodel.SampleMVVMCoroutinesViewModel

class SampleMVVMCoroutinesActivity : AppCompatActivity() {

    private lateinit var sampleMVVMCoroutinesViewModel: SampleMVVMCoroutinesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_m_v_v_m_coroutines)

        sampleMVVMCoroutinesViewModel = ViewModelProvider(this).get(SampleMVVMCoroutinesViewModel::class.java)

        sampleMVVMCoroutinesViewModel.user.observe(this, Observer { user ->
            println("DEBUG: $user")
        })

        sampleMVVMCoroutinesViewModel.setUserId("1")
    }

    override fun onDestroy() {
        super.onDestroy()
        sampleMVVMCoroutinesViewModel.cancelJobs()
    }
}