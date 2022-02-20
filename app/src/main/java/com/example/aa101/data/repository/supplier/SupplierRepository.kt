package com.example.aa101.data.repository.supplier

import com.example.aa101.data.room.model.Suppliers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

interface SupplierRepository {
    suspend fun addSupplier(supplierData: Suppliers)
    suspend fun updateSuppliers(updatedData: Suppliers)
    suspend fun supplierTMList(): Set<String>
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class AddSupplierRepoModule {

    @Binds
    abstract fun bindSupplierRepository(
        supplierRepoImpl: SupplierRepositoryImpl
    ): SupplierRepository
}