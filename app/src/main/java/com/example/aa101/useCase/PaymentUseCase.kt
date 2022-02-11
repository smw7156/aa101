package com.example.aa101.useCase

import com.example.aa101.data.repository.payment.PaymentRepository
import javax.inject.Inject

class PaymentUseCase @Inject constructor(
    private val paymentRepo: PaymentRepository
) {

}