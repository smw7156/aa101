package com.example.aa101.data.room.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Customers",
        indices = [Index(value = ["customerName"])])
data class Customers(
    @PrimaryKey(autoGenerate = false)
    val customerInitial: String,
    val customerName: String,
    val customerAddress: String?,
    val customerMobileNo: String?,
    val customerEmail: String?,
)
