package com.ganesh.androidfundamentals

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.broadcastreceivers.BroadcastReceiverActivity
import com.ganesh.androidfundamentals.contentproviders.ContentProvidersActivity
import com.ganesh.androidfundamentals.coroutines.SampleCoroutineActivity
import com.ganesh.androidfundamentals.multithreading.MultiThreadingActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonContentProviders.setOnClickListener {
            startActivity(Intent(this, ContentProvidersActivity::class.java))
        }

        buttonActivityFragments.setOnClickListener {

        }

        buttonBroadcastReceivers.setOnClickListener {
            startActivity(Intent(this, BroadcastReceiverActivity::class.java))
        }

        buttonServices.setOnClickListener {
            startActivity(Intent(this, MultiThreadingActivity::class.java))
        }

        buttonCoroutinesActivity.setOnClickListener {
            startActivity(Intent(this, SampleCoroutineActivity::class.java))
        }
    }
}
