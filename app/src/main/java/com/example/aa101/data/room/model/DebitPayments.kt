package com.example.aa101.data.room.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.NO_ACTION
import com.example.aa101.data.room.converter.DateToLongConverter
import java.time.LocalDate

@Entity(
    tableName = "DebitPayments",
    foreignKeys = [ForeignKey(
        entity = Suppliers::class,
        parentColumns = ["supplierTradeMark"],
        childColumns = ["beneficiary"],
        onDelete = NO_ACTION,
        onUpdate = CASCADE,
    )],
    indices = [Index(value = ["purpose"])]
)
data class DebitPayments(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @TypeConverters(value = [DateToLongConverter::class])
    val dateOfPayment: LocalDate,
    val amount: Double,
    val purpose: String,
    val detail: String?,
    val beneficiary: String?,
)
