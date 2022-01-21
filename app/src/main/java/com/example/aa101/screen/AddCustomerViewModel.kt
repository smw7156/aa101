package com.example.aa101.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddCustomerViewModel: ViewModel() {

    private var _customerName = MutableLiveData<String>()
    val customerName: LiveData<String> get() = _customerName

    private var _customerInitial = MutableLiveData<String>()
    val customerInitial: LiveData<String> get() = _customerInitial

    private var _customerMobNo = MutableLiveData<String>()
    val customerMobNo: LiveData<String> get() = _customerMobNo

    private var _customerEmail = MutableLiveData<String>()
    val customerEmail: LiveData<String> get() = _customerEmail

    private var _customerAddress = MutableLiveData<String>()
    val customerAddress: LiveData<String> get() = _customerAddress

    private var _customerInitialErrorMessage = MutableLiveData<String?>()
    val customerInitialErrorMessage: LiveData<String?> get() = _customerInitialErrorMessage

    fun setCustomerName(customerName: String) = _customerName.postValue(customerName)
    fun setCustomerInitial(customerInitial: String) = _customerInitial.postValue(customerInitial)
    fun setCustomerMobNo(customerName: String) = _customerMobNo.postValue(customerName)
    fun setCustomerEmail(customerEmail: String) = _customerEmail.postValue(customerEmail)
    fun setCustomerAddress(customerAddress: String) = _customerAddress.postValue(customerAddress)

    fun checkForValidInitial(customerInitial: String) {
        //TODO: validate customerInitial
    }

    fun onAddClicked() {
        // validate detail

        // add item in LDB
    }


}