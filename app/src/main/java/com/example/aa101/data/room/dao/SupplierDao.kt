package com.example.aa101.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import com.example.aa101.data.room.model.Suppliers

@Dao
interface SupplierDao {

    @Query("select * from Suppliers")
    suspend fun getAllSuppliers(): List<Suppliers>

    @Insert(onConflict = ABORT)
    suspend fun addSuppliers(suppliers: Suppliers)
}