package com.example.aa101.data.room.converter

import androidx.room.TypeConverter
import java.time.DateTimeException
import java.util.*

class DateToLongConverter {

    @TypeConverter
    @Throws(DateTimeException::class)
    fun fromDateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun fromLongToDate(long: Long): Date {
        return Date(long)
    }
}