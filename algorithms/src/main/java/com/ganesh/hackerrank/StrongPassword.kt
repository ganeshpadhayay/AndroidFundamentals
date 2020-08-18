package com.ganesh.hackerrank

fun main(args: Array<String>) {
    print(minimumNumber(4, "09"))
}

// Complete the minimumNumber function below.
fun minimumNumber(n: Int, password: String): Int {
    val len = password.length

    var charToBeAdded = 0
    if (!hasOneSpecialCharacterInIt(password)) {
        charToBeAdded++
    }
    if (!hasOneDigitInIt(password)) {
        charToBeAdded++
    }
    if (!hasOneSmallLetterInIt(password)) {
        charToBeAdded++
    }
    if (!hasOneCapitalLetterInIt(password)) {
        charToBeAdded++
    }

    return if (len + charToBeAdded < 6) {
        charToBeAdded + 6 - (len + charToBeAdded);
    } else {
        charToBeAdded
    }
}

fun hasOneSpecialCharacterInIt(password: String): Boolean {
    val specialCharString = "!@#$%^&*()-+"
    for (element in password) {
        if (specialCharString.contains(element) || element.toInt() == 134)
            return true
    }
    return false
}

fun hasOneSmallLetterInIt(password: String): Boolean {
    val regex = "abcdefghijklmnopqrstuvwxyz"
    for (element in password) {
        if (regex.contains(element))
            return true
    }
    return false
}

fun hasOneCapitalLetterInIt(password: String): Boolean {
    val regex = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    for (element in password) {
        if (regex.contains(element))
            return true
    }
    return false
}

fun hasOneDigitInIt(password: String): Boolean {
    val regex = "0123456789"
    for (element in password) {
        if (regex.contains(element))
            return true
    }
    return false
}
