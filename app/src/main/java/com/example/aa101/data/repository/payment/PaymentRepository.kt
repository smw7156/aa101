package com.example.aa101.data.repository.payment

import com.example.aa101.data.room.model.CreditPayments
import com.example.aa101.data.room.model.DebitPayments
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

interface PaymentRepository {

    suspend fun recordCreditPayment(paymentDetail: CreditPayments): Int
    suspend fun recordDebitPayment(paymentDetail: DebitPayments): Int
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class AddPaymentRepoModule {

    @Binds
    abstract fun bindPaymentRepoModule(paymentRepoImpl: PaymentRepoImpl): PaymentRepository
}