package com.ganesh.androidfundamentals.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

/**
 * Created by Ganesh Padhayay on 05/10/20.
 */
class CustomLocationPermissionHandler {

    companion object {
        const val REQUEST_LOCATION_PERMISSIONS = 101
    }

    private lateinit var onLocationPermissionListener: OnLocationPermissionChangedListener

    fun handleLocationPermissionPopup(activity: AppCompatActivity, title: String?, message: String?, positiveButtonText: String? = "Okay", listener: OnLocationPermissionChangedListener) {
        this.onLocationPermissionListener = listener

        if (checkPermissions(activity)) {
            onLocationPermissionListener.onLocationPermissionGranted()
        } else {
            AlertDialog.Builder(activity)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(positiveButtonText) { _, _ ->
                        requestPermissions(activity)
                    }
                    .setCancelable(false)
                    .show()
        }
    }

    private fun checkPermissions(context: Context): Boolean {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    }

    private fun requestPermissions(activity: AppCompatActivity) {
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), Companion.REQUEST_LOCATION_PERMISSIONS)
    }

    fun handlePermissionResults(requestCode: Int, grantResults: IntArray, activity: AppCompatActivity) {
        if (requestCode == REQUEST_LOCATION_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onLocationPermissionListener.onLocationPermissionGranted()
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    onLocationPermissionListener.onLocationPermissionDenied()
                } else {
                    onLocationPermissionListener.onLocationPermissionPermanentlyDenied()
                }
            }
        }
    }

    interface OnLocationPermissionChangedListener {
        fun onLocationPermissionGranted()

        fun onLocationPermissionDenied()

        fun onLocationPermissionPermanentlyDenied()
    }
}



