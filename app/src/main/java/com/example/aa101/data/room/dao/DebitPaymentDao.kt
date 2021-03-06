package com.example.aa101.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import com.example.aa101.data.room.model.DebitPayments

@Dao
interface DebitPaymentDao {

    @Insert(onConflict = ABORT)
    suspend fun addNewDebitPayment(payment: DebitPayments): Long

}