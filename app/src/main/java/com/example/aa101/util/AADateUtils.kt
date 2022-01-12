package com.example.aa101.util

import java.lang.StringBuilder

/**
 * @param monthNumber : 0 - 11
 * @return Full month name - complete name
 */
fun getMonthNameForNumber(monthNumber: Int): String {
    return when (monthNumber) {
        0 -> {
            "January"
        }
        1 -> {
            "February"
        }
        2 -> {
            "March"
        }
        3 -> {
            "April"
        }
        4 -> {
            "May"
        }
        5 -> {
            "June"
        }
        6 -> {
            "July"
        }
        7 -> {
            "August"
        }
        8 -> {
            "September"
        }
        9 -> {
            "October"
        }
        10 -> {
            "November"
        }
        11 -> {
            "December"
        }
        else -> {
            "Not Valid (0-11)"
        }
    }
}

/**
 * get short month name from fullName
 */
fun getShortMonthNameFromFullName(fullMonthName: String): String {
    var sc = StringBuilder(fullMonthName)
    return when {
        sc.length > 3 -> {
            sc.substring(0,3).toString()
        }
        sc.length == 3 -> {
            sc.toString()
        }
        else -> {
            "Not valid"
        }
    }
}

fun getShortMonthNameFromMonthNumber(monthNumber: Int): String {
    return getShortMonthNameFromFullName(getMonthNameForNumber(monthNumber))
}

