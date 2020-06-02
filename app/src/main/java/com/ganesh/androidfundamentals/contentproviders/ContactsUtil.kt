package com.ganesh.androidfundamentals.contentproviders

import android.app.Activity
import android.provider.ContactsContract
import android.text.TextUtils
import com.ganesh.androidfundamentals.main.AppConstants
import com.ganesh.androidfundamentals.R
import com.ganesh.androidfundamentals.contentproviders.data.ContactItem
import com.ganesh.androidfundamentals.contentproviders.data.ContactListItem
import com.ganesh.androidfundamentals.contentproviders.data.ContactListItem.Companion.TYPE_CONTACT
import com.ganesh.androidfundamentals.contentproviders.data.ContactListItem.Companion.TYPE_GROUP
import com.ganesh.androidfundamentals.contentproviders.data.ContactModel
import com.ganesh.androidfundamentals.contentproviders.data.GroupItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern


class ContactsUtil {

    interface ContactLoadListener {
        fun onContactsLoaded(contactList: ArrayList<ContactModel>)
    }

    companion object {

        private val PROJECTION = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        fun loadContacts(context: Activity?, listener: ContactLoadListener) {
            val contactList = ArrayList<ContactModel>()
            val nameList = ArrayList<String>()
            val numberList = ArrayList<String>()
            CoroutineScope(Dispatchers.IO).launch {
                val cr = context?.contentResolver
                val cur = cr?.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    PROJECTION,
                    null,
                    null,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
                )

                cur?.let {
                    while (it.moveToNext()) {

                        val name =
                            it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                        var phoneNo =
                            it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                        phoneNo = removeSpaceAndBracketP2P(phoneNo)

                        if (phoneNo.length > 10)
                            phoneNo = filterMobileNumberP2P(context, phoneNo)

                        if (isValidNumber(phoneNo)) {
                            if (!nameList.contains(name)) {
                                nameList.add(name)
                                numberList.add(phoneNo)
                                contactList.add(
                                    ContactModel(
                                        name,
                                        phoneNo
                                    )
                                )
                            } else if (!numberList.contains(phoneNo)) {
                                numberList.add(phoneNo)
                                contactList.add(
                                    ContactModel(
                                        name,
                                        phoneNo
                                    )
                                )
                            }
                        }
                    }
                    withContext(Dispatchers.Main) {
                        listener.onContactsLoaded(contactList)
                    }
                }
                cur?.close()
            }
        }

        private fun removeSpaceAndBracketP2P(inContactNumber: String): String? {
            var inContactNumber = inContactNumber
            return if (!TextUtils.isEmpty(inContactNumber)) {
                inContactNumber = inContactNumber.trim { it <= ' ' }
                inContactNumber = inContactNumber.replace("[^\\d+]".toRegex(), "")
                inContactNumber = inContactNumber.replace(" ".toRegex(), "")
                inContactNumber
            } else {
                ""
            }
        }

        private fun isValidNumber(mobile: String): Boolean {
            if (!TextUtils.isEmpty(mobile)) {
                try {
                    val firstNumber = Integer.parseInt(mobile[0].toString())
                    if (firstNumber > 4 && mobile.length == 10) {
                        return true
                    }
                } catch (ignored: NumberFormatException) {
                }

            }
            return false
        }

        private fun numberPatternChecker(query: String): String {
            val p1 = Pattern.compile("\\d{3}-\\d{3}-\\d{4}")
            val p2 = Pattern.compile("\\d{3}/\\d{3}/\\d{4}")
            val p3 = Pattern.compile("\\d{5}-\\d{5}")
            val p4 = Pattern.compile("\\d{5}/\\d{5}")
            val p5 = Pattern.compile("\\d{4}/\\d{4}/\\d{2}")
            val p6 = Pattern.compile("\\d{4}-\\d{4}-\\d{2}")
            val m1 = p1.matcher(query)
            val m2 = p2.matcher(query)
            val m3 = p3.matcher(query)
            val m4 = p4.matcher(query)
            val m5 = p5.matcher(query)
            val m6 = p6.matcher(query)

            return if (m1.find() || m2.find())
                query.substring(0, 3) + query.substring(4, 7) + query.substring(8)
            else if (m3.find() || m4.find())
                query.substring(0, 5) + query.substring(6)
            else if (m5.find() || m6.find())
                query.substring(0, 4) + query.substring(5, 9) + query.substring(10)
            else
                query
        }

        private fun extractNumber(query: String): String {
            if (query.length >= 15) {
                if (query.startsWith("0091-"))
                    return numberPatternChecker(query.substring(5))
            }
            if (query.length >= 14) {
                if (query.startsWith("+91-") || query.startsWith("+91.") || query.startsWith("091-") || query.startsWith(
                        "+91/"
                    )
                )
                    return numberPatternChecker(query.substring(4))
            }
            if (query.length >= 13) {
                if (query.startsWith("+91") || query.startsWith("091") || query.startsWith("91-") || query.startsWith(
                        "91/"
                    )
                )
                    return numberPatternChecker(query.substring(3))
            }
            if (query.length >= 12) {
                if (query.startsWith("91") || query.startsWith("0-"))
                    return numberPatternChecker(query.substring(2))
            }
            if (query.length >= 11) {
                if (query.startsWith("0"))
                    return numberPatternChecker(query.substring(1))
            }
            return numberPatternChecker(query)
        }

        private fun groupContactsAlphabetically(p2PContactEntity: ArrayList<ContactModel>): HashMap<Char, MutableList<ContactModel>> {
            val hashMap = HashMap<Char, MutableList<ContactModel>>()
            val extras = ArrayList<ContactModel>()

            for (item in p2PContactEntity) {
                val contact = item as ContactModel

                contact.name?.let { name ->
                    var firstChar = name[0]
                    firstChar = Character.toUpperCase(firstChar)

                    if (!Character.isLetter(firstChar)) {
                        extras.add(contact)
                    } else {
                        if (!hashMap.containsKey(firstChar)) {
                            val list = ArrayList<ContactModel>()
                            list.add(item)
                            hashMap[firstChar] = list
                        } else {
                            hashMap[firstChar]?.add(item)
                        }
                    }
                }
            }
            if (extras.size > 0) {
                hashMap['#'] = extras
            }
            return hashMap
        }

        private fun createList(mDataMap: HashMap<Char, MutableList<ContactModel>>): ArrayList<ContactListItem> {
            val al = ArrayList<ContactListItem>()

            for (key in mDataMap.keys) {
                if (key == '#')
                    continue
                val header = GroupItem(TYPE_GROUP)
                header.letter = key
                al.add(header)
                for (contact in mDataMap[key]!!) {
                    val item = ContactItem(TYPE_CONTACT)
                    item.contact = contact
                    al.add(item)
                }
            }
            if (mDataMap.containsKey('#')) {
                val header = GroupItem(TYPE_GROUP)
                header.letter = '#'
                al.add(header)
                for (contact in mDataMap[header.letter]!!) {
                    val item = ContactItem(TYPE_CONTACT)
                    item.contact = contact
                    al.add(item)
                }
            }
            return al
        }

        private fun getIndexArray(): Array<String?> {
            val letters = arrayOfNulls<String>(27)
            var i = 0
            var ch = 'A'
            while (ch <= 'Z') {
                letters[i] = ch.toString()
                i++
                ch++
            }
            letters[i] = "#"
            return letters
        }

        private fun filterMobileNumberP2P(
            activity: Activity,
            inContactNumber: String
        ): String? {
            var inContactNumber = inContactNumber
            return if (!TextUtils.isEmpty(inContactNumber)) {
                inContactNumber = inContactNumber.trim { it <= ' ' }
                var startIndex: Int = AppConstants.START_INDEX_ZERO
                inContactNumber = inContactNumber.replace("[^\\d+]".toRegex(), "")
                if (inContactNumber.startsWith(activity.getString(R.string.mobile_number_prefix_91))) {
                    startIndex = AppConstants.START_INDEX_THREE
                } else if (inContactNumber.startsWith(activity.getString(R.string.mobile_number_prefix_91_without_plus))) {
                    startIndex = AppConstants.START_INDEX_TWO
                } else if (inContactNumber.startsWith(activity.getString(R.string.mobile_number_prefix_0))) {
                    startIndex = AppConstants.START_INDEX_ONE
                }
                inContactNumber = inContactNumber
                    .substring(startIndex, inContactNumber.length).trim { it <= ' ' }
                    .replace(" ".toRegex(), "")
                inContactNumber
            } else {
                ""
            }
        }
    }
}