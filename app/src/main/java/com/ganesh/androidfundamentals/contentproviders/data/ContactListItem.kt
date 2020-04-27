package com.ganesh.androidfundamentals.contentproviders.data

abstract class ContactListItem {
    abstract val type: Int

    companion object {
        const val TYPE_GROUP = 0
        const val TYPE_CONTACT = 1
    }
}