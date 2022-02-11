package com.example.aa101.data.repository.payment

import com.example.aa101.data.room.model.CreditPayments
import com.example.aa101.data.room.model.DebitPayments

interface PaymentRepository {

    suspend fun recordCreditPayment(paymentDetail: CreditPayments)

    suspend fun recordDebitPayment(paymentDetail: DebitPayments)
}