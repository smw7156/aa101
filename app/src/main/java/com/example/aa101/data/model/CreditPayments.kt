package com.example.aa101.data.model

import androidx.room.*
import com.example.aa101.data.converter.DateToLongConverter
import java.util.*

@Entity(tableName = "CreditPayments",
    foreignKeys = [ForeignKey(
        entity = Customers::class,
        parentColumns = ["customerInitial"],
        childColumns = ["benefactor"],
        onDelete = ForeignKey.NO_ACTION,
        onUpdate = ForeignKey.CASCADE,
    )])
data class CreditPayments(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @TypeConverters(value = [DateToLongConverter::class])
    val dateOfPayment: Date,
    val amount: Double,
    val purpose: String,
    val detail: String?,
    val benefactor: String?,
)
