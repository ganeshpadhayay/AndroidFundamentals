package com.ganesh.androidfundamentals.contentproviders.data

class GroupItem(override val type: Int) : ContactListItem() {
    var letter = 0.toChar()
}