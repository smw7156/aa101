package com.example.aa101.util

import android.app.DatePickerDialog
import android.content.Context
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * @param monthNumber : 0 - 11
 * @return Full month name - complete name
 */
fun getMonthNameForNumber(monthNumber: Int, isCalendarMonth: Boolean): String {
    val month = if (isCalendarMonth) monthNumber + 1 else monthNumber
    return when (month) {
        1 -> {
            "January"
        }
        2 -> {
            "February"
        }
        3 -> {
            "March"
        }
        4 -> {
            "April"
        }
        5 -> {
            "May"
        }
        6 -> {
            "June"
        }
        7 -> {
            "July"
        }
        8 -> {
            "August"
        }
        9 -> {
            "September"
        }
        10 -> {
            "October"
        }
        11 -> {
            "November"
        }
        12 -> {
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
            sc.substring(0, 3).toString()
        }
        sc.length == 3 -> {
            sc.toString()
        }
        else -> {
            "Not valid"
        }
    }
}

fun getShortMonthNameFromMonthNumber(monthNumber: Int, isCalendarMonth: Boolean = false): String {
    return getShortMonthNameFromFullName(getMonthNameForNumber(monthNumber, isCalendarMonth))
}

fun LocalDate.toShowDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy")
    return this.format(formatter)
}

fun datePickerDialog(context: Context, selectedDate: (LocalDate) -> Unit) {

    var cal = Calendar.getInstance()
    val thisYear = cal.get(Calendar.YEAR)
    val thisMonth = cal.get(Calendar.MONTH)
    val thisDay = cal.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context, { view, year, calendarMonth, dayOfMonth ->
            selectedDate(LocalDate.of(year, calendarMonth + 1, dayOfMonth))
        },
        thisYear,
        thisMonth,
        thisDay
    );

    datePickerDialog.show()
}
