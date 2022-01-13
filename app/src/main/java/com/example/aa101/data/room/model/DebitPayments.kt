package com.example.aa101.data.room.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.NO_ACTION
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.aa101.data.room.converter.DateToLongConverter
import java.util.*

@Entity(
    tableName = "DebitPayments",
    foreignKeys = [ForeignKey(
        entity = Suppliers::class,
        parentColumns = ["supplierTradeMark"],
        childColumns = ["beneficiary"],
        onDelete = NO_ACTION,
        onUpdate = CASCADE,
    )]
)
data class DebitPayments(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @TypeConverters(value = [DateToLongConverter::class])
    val dateOfPayment: Date,
    val amount: Double,
    val purpose: String,
    val detail: String?,
    val beneficiary: String?,
)
