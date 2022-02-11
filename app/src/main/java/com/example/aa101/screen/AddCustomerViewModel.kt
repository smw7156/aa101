package com.example.aa101.screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aa101.data.room.model.Customers
import com.example.aa101.useCase.CustomerUseCase
import com.example.aa101.util.empty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCustomerViewModel @Inject constructor(
    private val customerUseCase: CustomerUseCase,
): ViewModel() {

    private final val TAG = "AddCustomerViewModel"

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
        if (_customerName.value.isNullOrEmpty()) {
            Log.e(TAG, "customer name is empty")
            return
        }
        if (_customerInitial.value.isNullOrEmpty()) {
            Log.e(TAG, "customer Initial is empty")
            _customerInitialErrorMessage.postValue("Field cannot be empty, and must be unique")
            return
        }
        if (_customerEmail.value.isNullOrEmpty()) {
            Log.e(TAG, "customer email is empty")
            return
        }
        if (_customerMobNo.value.isNullOrEmpty()) {
            Log.e(TAG, "customer Mob no. is empty")
            return
        }

        Log.i(TAG, "adding customer in DB")

        viewModelScope.launch(Dispatchers.IO) {
            customerUseCase.addCustomer(
                Customers(
                    customerName = _customerName.value.toString() ,
                    customerInitial = _customerInitial.value.toString(),
                    customerMobileNo = _customerMobNo.value.toString(),
                    customerEmail = _customerEmail.value.toString(),
                    customerAddress = _customerAddress.value.toString(),
                )
            )
        }
    }


}