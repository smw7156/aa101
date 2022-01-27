package com.example.aa101.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import com.example.aa101.data.room.model.Customers

@Dao
interface CustomerDao {

    @Insert(onConflict = ABORT)
    suspend fun addCustomer(customer: Customers)

    @Query("Select * from Customers")
    suspend fun getAllCustomer(): List<Customers>
}