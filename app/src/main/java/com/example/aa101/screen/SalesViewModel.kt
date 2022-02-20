package com.example.aa101.screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aa101.useCase.CustomerUseCase
import com.example.aa101.useCase.SalesUseCase
import com.example.aa101.useCase.SupplierUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SalesViewModel @Inject constructor(
    private val supplierUseCase: SupplierUseCase,
    private val customerUseCase: CustomerUseCase,
    private val salesUseCase: SalesUseCase,
): ViewModel() {

    private val TAG = "SalesViewModel"

    private var _supplierList = MutableLiveData<Set<String>>()
    val supplierList : LiveData<Set<String>> get() = _supplierList

    private var _supplier = MutableLiveData<String>()
    val supplier: LiveData<String> get() = _supplier

    private var _customerList = MutableLiveData<Set<String>>()
    val customerList : LiveData<Set<String>> get() = _customerList

    private var _customer = MutableLiveData<String>()
    val customer: LiveData<String> get() = _customer

    private var _moveToSalesEntry = MutableLiveData<Boolean>(false)
    val moveToSalesEntry : LiveData<Boolean> get() = _moveToSalesEntry

    fun getSupplierTMList() {
        viewModelScope.launch(Dispatchers.IO) {
            _supplierList.postValue(supplierUseCase.getTMOfSuppliers())
        }
    }

    fun getCustomerInitialList() {
        viewModelScope.launch(Dispatchers.IO) {
            _customerList.postValue(customerUseCase.getInitialsOfCustomer())
        }
    }

    fun onProceedButtonClicked() {
        Log.i(TAG,"ProceedButton clicked")
        validateFieldsAndProceed()
    }

    private fun validateFieldsAndProceed() {
        _moveToSalesEntry.postValue(true)
    }

}