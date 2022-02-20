package com.example.aa101.data.repository.sales

import com.example.aa101.data.room.model.SalesDetail
import com.example.aa101.data.room.model.SalesHeaders
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

interface SalesRepository {

    suspend fun createHeader(header: SalesHeaders)
    suspend fun addSaleInHeader(detail: SalesDetail)

}

@Module
@InstallIn(ViewModelComponent::class)
abstract class AddSalesModule {

    @Binds
    abstract fun bindSalesRepo(
        salesRepoImpl: SalesRepoImpl
    ): SalesRepository
}