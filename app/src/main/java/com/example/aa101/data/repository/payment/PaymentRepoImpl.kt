package com.example.aa101.data.repository.payment

import com.example.aa101.data.room.dao.CreditPaymentDao
import com.example.aa101.data.room.dao.DebitPaymentDao
import com.example.aa101.data.room.model.CreditPayments
import com.example.aa101.data.room.model.DebitPayments
import javax.inject.Inject

class PaymentRepoImpl @Inject constructor(
    private val creditPaymentDao: CreditPaymentDao,
    private val debitPaymentDao: DebitPaymentDao,
): PaymentRepository {
    override suspend fun recordCreditPayment(paymentDetail: CreditPayments) {
        creditPaymentDao.addNewCreditPayment(paymentDetail)
    }

    override suspend fun recordDebitPayment(paymentDetail: DebitPayments) {
        debitPaymentDao.addNewDebitPayment(paymentDetail)
    }
}