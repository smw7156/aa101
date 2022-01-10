package com.example.aa101.data.room.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.NO_ACTION
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.aa101.data.room.converter.DateToLongConverter
import java.util.*

@Entity(tableName = "SalesHeaders",
        foreignKeys = [ForeignKey(
            entity = Suppliers::class,
            parentColumns = ["supplierTradeMark"],
            childColumns = ["partyTradeMark"],
            onDelete = NO_ACTION,
            onUpdate = CASCADE,
        )]
)
data class SalesHeaders(
    val id: Int,
    @TypeConverters(value = [DateToLongConverter::class])
    val dateOfReceived: Date,
    @TypeConverters(value = [DateToLongConverter::class])
    val dateOfSale: Date,
    val partyTradeMark: String,
    val noOfBoxs: Int,
    val transportMedium: String,
    val transportDetail: String,
)
