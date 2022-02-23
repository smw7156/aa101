package com.example.aa101.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import com.example.aa101.data.room.model.SalesHeaders

@Dao
interface SalesHeaderDao {

    @Insert(onConflict = ABORT)
    suspend fun addNewSalesHeader(salesHeaders: SalesHeaders): Long

    @Query("SELECT * FROM SalesHeaders WHERE id = :headerId")
    suspend fun getSalesHeaderForId(headerId: Int): SalesHeaders
}