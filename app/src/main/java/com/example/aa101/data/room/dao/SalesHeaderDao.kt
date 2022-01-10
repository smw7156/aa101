package com.example.aa101.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import com.example.aa101.data.room.model.SalesHeaders

@Dao
interface SalesHeaderDao {

    @Insert(onConflict = ABORT)
    suspend fun addNewSalesHeader(salesHeaders: SalesHeaders)
}