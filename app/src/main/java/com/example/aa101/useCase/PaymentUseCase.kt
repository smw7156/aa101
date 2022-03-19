package com.example.aa101.useCase

import com.example.aa101.data.repository.customer.CustomerRepository
import com.example.aa101.data.repository.payment.PaymentRepository
import com.example.aa101.data.repository.supplier.SupplierRepository
import com.example.aa101.data.room.model.CreditPayments
import com.example.aa101.data.room.model.DebitPayments
import javax.inject.Inject

class PaymentUseCase @Inject constructor(
    private val paymentRepo: PaymentRepository,
    private val customerRepository: CustomerRepository,
    private val supplierRepository: SupplierRepository,
) {

    suspend fun recordCreditPayment(paymentDetail: CreditPayments): Int {
        return paymentRepo.recordCreditPayment(paymentDetail)
    }

    suspend fun recordDebitPayment(paymentDetail: DebitPayments): Int {
        return paymentRepo.recordDebitPayment(paymentDetail)
    }

    suspend fun getBeneficiaryList(): Set<String> {
        return supplierRepository.supplierTMList()
    }

    suspend fun getBenefactorList(): Set<String> {
        return customerRepository.getCustomersInitials()
    }
}