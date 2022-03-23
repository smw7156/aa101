package com.example.aa101.data.room.model

import androidx.room.*
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
    )],
    indices = [Index(value = ["purpose"])]
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
