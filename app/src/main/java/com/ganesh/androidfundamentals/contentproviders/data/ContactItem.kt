package com.ganesh.androidfundamentals.contentproviders.data

class ContactItem(override val type: Int) : ContactListItem() {
    var contact: ContactModel? = null

}