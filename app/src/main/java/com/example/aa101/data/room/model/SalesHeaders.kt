package com.example.aa101.data.room.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.NO_ACTION
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.aa101.data.room.converter.DateToLongConverter
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(
    tableName = "SalesHeaders",
    foreignKeys = [ForeignKey(
        entity = Suppliers::class,
        parentColumns = ["supplierTradeMark"],
        childColumns = ["partyTradeMark"],
        onDelete = NO_ACTION,
        onUpdate = CASCADE,
    )]
)
@Parcelize
data class SalesHeaders(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @TypeConverters(value = [DateToLongConverter::class])
    val dateOfReceived: Date,
    @TypeConverters(value = [DateToLongConverter::class])
    val dateOfSale: Date,
    val partyTradeMark: String,
    val noOfBoxs: Int,
    // val boxType: String, //is Missing TODO() <-
    val transportMedium: String,
    val transportDetail: String,
): Parcelable
