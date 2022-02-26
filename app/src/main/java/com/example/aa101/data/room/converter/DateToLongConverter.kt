package com.example.aa101.data.room.converter

import androidx.room.TypeConverter
import java.time.DateTimeException
import java.time.LocalDate

/** Converting LocalDate to long
 * and vice-versa*/
class DateToLongConverter {

    @TypeConverter
    @Throws(DateTimeException::class)
    fun fromDateToLong(date: LocalDate): Long {
        val res = date.toEpochDay()
        return res
    }

    @TypeConverter
    fun fromLongToDate(long: Long): LocalDate {
        val res = LocalDate.ofEpochDay(long)
        return res
    }
}