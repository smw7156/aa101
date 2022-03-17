package com.example.aa101.useCase

import com.example.aa101.data.repository.supplier.SupplierRepository
import com.example.aa101.data.room.model.Suppliers
import javax.inject.Inject

class SupplierUseCase @Inject constructor(
    private val supplierRepo: SupplierRepository
){
    suspend fun addSuppliers(supplier: Suppliers): Int {
        return supplierRepo.addSupplier(supplier)
    }

    suspend fun getTMOfSuppliers(): Set<String> {
        return supplierRepo.supplierTMList()
    }
}