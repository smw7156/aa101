package com.example.aa101.data.repository.supplier

import com.example.aa101.data.room.dao.SupplierDao
import com.example.aa101.data.room.model.Suppliers
import javax.inject.Inject

class SupplierRepositoryImpl @Inject constructor(
    private val supplierDao: SupplierDao
): SupplierRepository {

    override suspend fun addSupplier(supplierData: Suppliers): Int {
        return supplierDao.addSuppliers(supplierData).toInt()
    }

    override suspend fun updateSuppliers(updatedData: Suppliers) {
        TODO("Not yet implemented")
    }

    override suspend fun supplierTMList(): Set<String> {
//        val supplierList = supplierDao.getSupplierTradeMarks()
        return supplierDao.getSupplierTradeMarks().toSet()
    }
}