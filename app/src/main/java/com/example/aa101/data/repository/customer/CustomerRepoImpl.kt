package com.example.aa101.data.repository.customer

import com.example.aa101.data.room.dao.CustomerDao
import com.example.aa101.data.room.model.Customers
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val customerDao: CustomerDao
): CustomerRepository {

    override suspend fun addCustomer(customerData: Customers): Int {
        return customerDao.addCustomer(customerData).toInt()
    }

    override suspend fun updateCustomer(updatedData: Customers) {
        TODO("Not yet implemented")
    }

    override suspend fun getCustomersInitials(): Set<String> {
        return customerDao.getAllCustomerInitial().toSet()
    }
}