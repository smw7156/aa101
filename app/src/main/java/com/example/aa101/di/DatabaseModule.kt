package com.example.aa101.di

import com.example.aa101.data.room.SalesDatabase
import com.example.aa101.data.room.dao.*
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

    @Provides
    fun provideSalesHeaderDao(salesDatabase: SalesDatabase): SalesHeaderDao {
        return salesDatabase.salesHeaderDao()
    }

    @Provides
    fun providesSalesDetailDao(salesDatabase: SalesDatabase): SalesDetailDao {
        return salesDatabase.salesDetailDao()
    }

}