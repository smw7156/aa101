package com.example.aa101.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customers")
data class Customers(
    @PrimaryKey(autoGenerate = false)
    val customerInitial: String,
    val customerName: String,
    val customerAddress: String,
    val customerMobileNo: String,
    val customerEmail: String,
)
