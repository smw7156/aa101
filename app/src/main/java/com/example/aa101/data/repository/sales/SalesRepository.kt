package com.example.aa101.data.repository.sales

import com.example.aa101.data.room.model.SalesDetail
import com.example.aa101.data.room.model.SalesHeaders
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

interface SalesRepository {

    suspend fun createHeader(header: SalesHeaders): Int
    suspend fun addSaleInHeader(detail: SalesDetail): Int
    suspend fun getHeaderDetailForId(headerId: Int): SalesHeaders
    suspend fun getSaleDetailForId(salesId: Int): SalesDetail
    suspend fun getSalesForHeader(headerId: Int): List<SalesDetail>

}

@Module
@InstallIn(ViewModelComponent::class)
abstract class AddSalesModule {

    @Binds
    abstract fun bindSalesRepo(
        salesRepoImpl: SalesRepoImpl
    ): SalesRepository
}