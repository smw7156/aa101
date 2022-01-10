package com.example.aa101.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.NO_ACTION

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
    val dateOfReceived: String,
    val dateOfSale: String,
    val partyTradeMark: String,
    val noOfBoxs: Int,
    val transportMedium: String,
    val transportDetail: String,
)
