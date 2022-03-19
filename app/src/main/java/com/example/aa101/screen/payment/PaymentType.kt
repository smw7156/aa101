package com.example.aa101.screen.payment

enum class PaymentType(val strValue: String) {
    DEBIT("debit"), // outgoing
    CREDIT("credit"), // incoming
}