package com.ganesh.androidfundamentals.workmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ganesh.androidfundamentals.R
import com.ganesh.androidfundamentals.workmanager.backgroundwork.WorkManagerInBackgroundActivity
import com.ganesh.androidfundamentals.workmanager.imageblur.SelectImageActivity
import kotlinx.android.synthetic.main.activity_sample_work_manager.*

class SampleWorkManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_work_manager)

        imageBlurWorkButton?.setOnClickListener {
            startActivity(Intent(this, SelectImageActivity::class.java))
        }

        workManagerInBackground?.setOnClickListener {
            startActivity(Intent(this, WorkManagerInBackgroundActivity::class.java))
        }
    }
}