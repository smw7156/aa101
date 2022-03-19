package com.example.aa101.screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aa101.data.room.model.CreditPayments
import com.example.aa101.data.room.model.DebitPayments
import com.example.aa101.screen.payment.PaymentType
import com.example.aa101.useCase.PaymentUseCase
import com.example.aa101.util.empty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class RecordPaymentViewModel @Inject constructor(val paymentUseCase: PaymentUseCase) : ViewModel() {

    private val TAG = "RecordPaymentViewModel"

    private var _paymentType = MutableLiveData<PaymentType>()
    val paymentType: LiveData<PaymentType> get() = _paymentType

    private var _paymentDate = MutableLiveData<LocalDate>()
    val paymentDate: LiveData<LocalDate> get() = _paymentDate

    private var _paymentAmount = MutableLiveData<Double>()
    val paymentAmount: LiveData<Double> get() = _paymentAmount

    private var _beneficiaryList = MutableLiveData<Set<String>>()
    val beneficiaryList: LiveData<Set<String>> get() = _beneficiaryList

    private var _benefactorList = MutableLiveData<Set<String>>()
    val benefactorList: LiveData<Set<String>> get() = _benefactorList

//    private var _selectedBeneficiary = MutableLiveData<String>()
//    val selectedBeneficiary: LiveData<String> get() = _selectedBeneficiary
//
//    private var _selectedBenefactor = MutableLiveData<String>()
//    val selectedBenefactor: LiveData<String> get() = _selectedBenefactor

    private var _concernedParty = MutableLiveData<String>()
    val concernedParty: LiveData<String> get() = _concernedParty

    private var _paymentDetail = MutableLiveData<String>()
    val paymentDetail: LiveData<String> get() = _paymentDetail

    private var _isPaymentAdded = MutableLiveData<Boolean>(false)
    val isPaymentAdded: LiveData<Boolean> get() = _isPaymentAdded

    fun setPaymentType(paymentType: PaymentType) = _paymentType.postValue(paymentType)
    fun setPaymentDate(paymentDate: LocalDate) = _paymentDate.postValue(paymentDate)
    fun setPaymentAmount(amount: Double) = _paymentAmount.postValue(amount)
    fun setPaymentDetail(detail: String) = _paymentDetail.postValue(detail)
    fun setBenef(name: String) = _concernedParty.postValue(name)

    fun getBenefactorList() {
        viewModelScope.launch(Dispatchers.IO) {
            _benefactorList.postValue(paymentUseCase.getBenefactorList())
        }
    }

    fun getBeneficiaryList() {
        viewModelScope.launch(Dispatchers.IO) {
            _beneficiaryList.postValue(paymentUseCase.getBeneficiaryList())
        }
    }

    fun recordPaymentClick() {
        when (_paymentType.value) {
            PaymentType.CREDIT -> {
                val creditPayment = CreditPayments(
                    0,
                    _paymentDate.value!!,
                    _paymentAmount.value!!,
                    String.empty(),
                    _paymentDetail.value,
                    _concernedParty.value
                )
                viewModelScope.launch(Dispatchers.IO) {
                    val id = paymentUseCase.recordCreditPayment(creditPayment)
                    Log.i(TAG,"credit entry added for id: $id")
                    if (id > 0) {
                        _isPaymentAdded.postValue(true)
                    }
                }
            }
            PaymentType.DEBIT -> {
                val debitPayment = DebitPayments(
                    0,
                    _paymentDate.value!!,
                    _paymentAmount.value!!,
                    String.empty(),
                    _paymentDetail.value,
                    _concernedParty.value
                )
                viewModelScope.launch(Dispatchers.IO) {
                    val id = paymentUseCase.recordDebitPayment(debitPayment)
                    Log.i(TAG,"debit entry added for id: $id")
                    if (id > 0) {
                        _isPaymentAdded.postValue(true)
                    }
                }
            }
            else -> {
                return
            }
        }
    }

    fun resetPaymentAdded() = _isPaymentAdded.postValue(false)

}