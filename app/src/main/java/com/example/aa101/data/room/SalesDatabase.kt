package com.example.aa101.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.aa101.data.room.converter.DateToLongConverter
import com.example.aa101.data.room.dao.*
import com.example.aa101.data.room.model.*

@Database(entities = [SalesDatabase::class,SalesHeaders::class,Suppliers::class,Customers::class,
                        CreditPayments::class, DebitPayments::class], version = 1, exportSchema = false)
@TypeConverters(value = [DateToLongConverter::class])
abstract class SalesDatabase: RoomDatabase() {

    abstract fun salesDetailDao(): SalesDetailDao
    abstract fun salesHeaderDao(): SalesHeaderDao
    abstract fun suppliersDao(): SupplierDao
    abstract fun customerDao(): CustomerDao
    abstract fun debitPaymentDao(): DebitPaymentDao
    abstract fun creditPaymentDao(): CreditPaymentDao

    companion object {

        @Volatile
        private var INSTANCE : SalesDatabase? = null

        fun getRoomDBInstance(ctx: Context): SalesDatabase {
            val ins = INSTANCE
            if (ins != null) {
                return ins
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    SalesDatabase::class.java,
                    "SalesDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}