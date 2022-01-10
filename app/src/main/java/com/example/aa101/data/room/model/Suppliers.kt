package com.example.aa101.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Suppliers")
data class Suppliers(
    @PrimaryKey(autoGenerate = false)
    val supplierTradeMark: String,
    val supplierName: String,
    val supplierMobileNo: String?,
    val supplierAddress: String?,
    val supplierEmail: String?,
)
