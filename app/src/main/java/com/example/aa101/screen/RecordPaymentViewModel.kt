package com.example.aa101.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aa101.screen.payment.PaymentType
import java.time.LocalDate
import java.util.*

class RecordPaymentViewModel : ViewModel() {

    private var _paymentType = MutableLiveData<PaymentType>()
    val paymentType: LiveData<PaymentType> get() = _paymentType

    private var _paymentDate = MutableLiveData<LocalDate>()
    val paymentDate: LiveData<LocalDate> get() = _paymentDate

    private var _paymentAmount = MutableLiveData<Double>()
    val paymentAmount: LiveData<Double> get() = _paymentAmount

    private var _beneficiaryList = MutableLiveData<List<String>>()
    val beneficiaryList: LiveData<List<String>> get() = _beneficiaryList

    private var _benefactorList = MutableLiveData<List<String>>()
    val benefactorList: LiveData<List<String>> get() = _benefactorList

    private var _selectedBeneficiary = MutableLiveData<String>()
    val selectedBeneficiary: LiveData<String> get() = _selectedBeneficiary

    private var _selectedBenefactor = MutableLiveData<String>()
    val selectedBenefactor: LiveData<String> get() = _selectedBenefactor

    private var _paymentDetail = MutableLiveData<String>()
    val paymentDetail: LiveData<String> get() = _paymentDetail


}