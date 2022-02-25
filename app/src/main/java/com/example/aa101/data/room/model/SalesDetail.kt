package com.example.aa101.data.room.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.NO_ACTION
import androidx.room.PrimaryKey

@Entity(
    tableName = "SalesDetail",
    foreignKeys = [ForeignKey(
        entity = SalesHeaders::class,
        parentColumns = ["id"],
        childColumns = ["headerId"],
        onDelete = NO_ACTION,
        onUpdate = CASCADE,
    ), ForeignKey(
        entity = Customers::class,
        parentColumns = ["customerInitial"],
        childColumns = ["buyer"],
        onUpdate = CASCADE,
        onDelete = NO_ACTION,
    )
    ]
)
data class SalesDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val headerId: Int,
    val itemDetail: String,
    val grossWeight: Double,
    val rate: Double,
    val buyer: String,
)
