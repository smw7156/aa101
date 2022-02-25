package com.example.aa101.data.room.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.aa101.data.room.converter.DateToLongConverter
import java.time.LocalDate

@Entity(
    tableName = "CreditPayments",
    foreignKeys = [ForeignKey(
        entity = Customers::class,
        parentColumns = ["customerInitial"],
        childColumns = ["benefactor"],
        onDelete = ForeignKey.NO_ACTION,
        onUpdate = ForeignKey.CASCADE,
    )]
)
data class CreditPayments(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @TypeConverters(value = [DateToLongConverter::class])
    val dateOfPayment: LocalDate,
    val amount: Double,
    val purpose: String,
    val detail: String?,
    val benefactor: String?,
)
