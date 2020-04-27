package com.ganesh.androidfundamentals

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.contentproviders.ContentProvidersActivity
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

        }

        buttonServices.setOnClickListener {

        }
    }
}
