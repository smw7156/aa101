package com.example.aa101.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import com.example.aa101.data.room.model.Customers

@Dao
interface CustomerDao {

    @Insert(onConflict = ABORT)
    suspend fun addCustomer(customer: Customers)
}