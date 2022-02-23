package com.example.aa101.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import com.example.aa101.data.room.model.SalesDetail

@Dao
interface SalesDetailDao {

    @Insert(onConflict = ABORT)
    suspend fun addNewSaleEntry(salesDetail: SalesDetail): Long

    @Query("SELECT * FROM SalesDetail WHERE id = :salesId")
    suspend fun getSalesDetailForId(salesId: Int): SalesDetail

    @Query("SELECT * FROM SalesDetail WHERE headerId = :headerId")
    suspend fun getSalesForHeaderId(headerId: Int): List<SalesDetail>

}