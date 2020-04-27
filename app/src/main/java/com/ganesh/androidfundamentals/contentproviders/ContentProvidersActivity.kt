package com.ganesh.androidfundamentals.contentproviders

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ganesh.androidfundamentals.R
import com.ganesh.androidfundamentals.contentproviders.data.ContactModel

class ContentProvidersActivity : AppCompatActivity(), ContactsUtil.ContactLoadListener {

    private val PERMISSIONS_REQUEST_P2P_READ_CONTACTS = 201

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_providers)
        fetchContactsWithPermissionHandling()
    }

    private fun fetchContactsWithPermissionHandling() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), PERMISSIONS_REQUEST_P2P_READ_CONTACTS)
                return
            }
        }
        ContactsUtil.loadContacts(this, this)
    }

    override fun onContactsLoaded(contactList: ArrayList<ContactModel>) {
        println("Total number of contacts: "+contactList.size)
    }
}
