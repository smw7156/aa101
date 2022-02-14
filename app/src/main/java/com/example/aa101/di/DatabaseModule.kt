package com.example.aa101.di

import com.example.aa101.data.room.SalesDatabase
import com.example.aa101.data.room.dao.CreditPaymentDao
import com.example.aa101.data.room.dao.CustomerDao
import com.example.aa101.data.room.dao.DebitPaymentDao
import com.example.aa101.data.room.dao.SupplierDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Added this file for telling how to get the instances of Daos in the app
 */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideSupplierDao(salesDatabase: SalesDatabase): SupplierDao {
        return salesDatabase.suppliersDao()
    }

    @Provides
    fun provideCustomerDao(salesDatabase: SalesDatabase): CustomerDao {
        return salesDatabase.customerDao()
    }

    @Provides
    fun provideCreditPaymentDao(salesDatabase: SalesDatabase): CreditPaymentDao {
        return salesDatabase.creditPaymentDao()
    }
    @Provides
    fun provideDebitPaymentDao(salesDatabase: SalesDatabase): DebitPaymentDao {
        return salesDatabase.debitPaymentDao()
    }

    //todo = add rest of the daos

}