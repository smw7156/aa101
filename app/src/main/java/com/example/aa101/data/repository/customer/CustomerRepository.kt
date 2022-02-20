package com.example.aa101.data.repository.customer

import com.example.aa101.data.room.model.Customers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

interface CustomerRepository {
    suspend fun addCustomer(customerData: Customers)
    suspend fun updateCustomer(updatedData: Customers)
    suspend fun getCustomersInitials(): Set<String>
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class AddCustomerRepoModule{

    @Binds
    abstract fun bindCustomerRepository(
        customerRepoImpl: CustomerRepositoryImpl
    ): CustomerRepository
}