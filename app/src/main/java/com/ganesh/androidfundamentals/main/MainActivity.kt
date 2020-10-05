package com.ganesh.androidfundamentals.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ganesh.androidfundamentals.R
import com.ganesh.androidfundamentals.broadcastreceivers.BroadcastReceiverActivity
import com.ganesh.androidfundamentals.contentproviders.ContentProvidersActivity
import com.ganesh.androidfundamentals.coroutines.CoroutinesActivity
import com.ganesh.androidfundamentals.multithreading.MultiThreadingActivity
import com.ganesh.androidfundamentals.navigationcomponents.SampleNavigationComponentActivity
import com.ganesh.androidfundamentals.samplemvvmproject.SampleMVVMCoroutinesActivity
import com.ganesh.androidfundamentals.workmanager.SampleWorkManagerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonContentProviders.setOnClickListener {
            startActivity(Intent(this, ContentProvidersActivity::class.java))
        }

        buttonActivityFragments.setOnClickListener {
            initLocationPermissions()
        }

        buttonBroadcastReceivers.setOnClickListener {
            startActivity(Intent(this, BroadcastReceiverActivity::class.java))
        }

        buttonServices.setOnClickListener {
            startActivity(Intent(this, MultiThreadingActivity::class.java))
        }

        buttonCoroutinesActivity.setOnClickListener {
            startActivity(Intent(this, CoroutinesActivity::class.java))
        }

        button_sample_MVVM_coroutines?.setOnClickListener {
            startActivity(Intent(this, SampleMVVMCoroutinesActivity::class.java))
        }

        button_navigation_components?.setOnClickListener {
            startActivity(Intent(this, SampleNavigationComponentActivity::class.java))
        }

        buttonWorkManagerActivity?.setOnClickListener {
            startActivity(Intent(this, SampleWorkManagerActivity::class.java))
        }
    }

    private var customLocationPermissionHandler = CustomLocationPermissionHandler()

    private fun initLocationPermissions() {
        customLocationPermissionHandler.handleLocationPermissionPopup(this,
                "Please allow us to access this device's location.",
                "This app collects location data to track your promotional activities when the app is closed or not in use.",
                "Ok",
                object : CustomLocationPermissionHandler.OnLocationPermissionChangedListener {
                    override fun onLocationPermissionGranted() {
                        //granted permission
                        Toast.makeText(this@MainActivity, "Granted", Toast.LENGTH_SHORT).show()
                    }

                    override fun onLocationPermissionDenied() {
                        //denied permission
                        Toast.makeText(this@MainActivity, "Please give permission to access this feature", Toast.LENGTH_SHORT).show()
                        initLocationPermissions()
                    }

                    override fun onLocationPermissionPermanentlyDenied() {
                        //permanently denied permission
                        Toast.makeText(this@MainActivity, "You have permanently denied the permission, please go to settings and provide location permission", Toast.LENGTH_SHORT).show()
                    }
                })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        customLocationPermissionHandler.handlePermissionResults(requestCode, grantResults, this)
    }
}
