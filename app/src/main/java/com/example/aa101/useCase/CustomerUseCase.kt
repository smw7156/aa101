package com.example.aa101.useCase

import com.example.aa101.data.repository.customer.CustomerRepository
import com.example.aa101.data.room.model.Customers
import javax.inject.Inject


class CustomerUseCase @Inject constructor(
    private val customerRepo: CustomerRepository
) {

    suspend fun addCustomer(customer: Customers) {
        customerRepo.addCustomer(customer)
    }

    suspend fun getInitialsOfCustomer(): Set<String> {
        return customerRepo.getCustomersInitials()
    }
}