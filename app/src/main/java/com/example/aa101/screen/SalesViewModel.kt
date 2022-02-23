package com.example.aa101.screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aa101.data.room.model.SalesHeaders
import com.example.aa101.useCase.CustomerUseCase
import com.example.aa101.useCase.SalesUseCase
import com.example.aa101.useCase.SupplierUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
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

    private var _receivedDate = MutableLiveData<Date>()
    val receivedDate: LiveData<Date> get() = _receivedDate

    private var _salesDate = MutableLiveData<Date>()
    val salesDate: LiveData<Date> get() = _salesDate

    private var _noOfBox = MutableLiveData<Int>()
    val noOfBox: LiveData<Int> get() = _noOfBox

    private var _typeOfBox = MutableLiveData<String>()
    val typeOfBox: LiveData<String> get() = _typeOfBox

    private var _transportDetail = MutableLiveData<String>()
    val transportDetail: LiveData<String> get() = _transportDetail

    private var _transportMedium = MutableLiveData<String>()
    val transportMedium: LiveData<String> get() = _transportMedium

    private var _itemExtraDetails = MutableLiveData<String>()
    val itemExtraDetails: LiveData<String> get() = _itemExtraDetails

    private var _moveToSalesEntry = MutableLiveData<SalesHeaders>(null)
    val moveToSalesEntry : LiveData<SalesHeaders> get() = _moveToSalesEntry

    fun getSupplierTMList() {
        viewModelScope.launch(Dispatchers.IO) {
            _supplierList.postValue(supplierUseCase.getTMOfSuppliers())
        }
    }

    fun setReceivedDate(rcvDate: Date) = _receivedDate.postValue(rcvDate)
    fun setSalesDate(saleDate: Date) = _salesDate.postValue(saleDate)

    fun setSelectedParty(partyTM: String) = _supplier.postValue(partyTM)
    fun setTypeOfBox(boxType: String) = _typeOfBox.postValue(boxType)
    fun setTransportMedium(transportMedium: String) = _transportMedium.postValue(transportMedium)
    fun setTransportDetail(transportDetail: String) = _transportDetail.postValue(transportDetail)

    fun setNoOfBox(boxes: Int) = _noOfBox.postValue(boxes)

    fun getCustomerInitialList() {
        viewModelScope.launch(Dispatchers.IO) {
            _customerList.postValue(customerUseCase.getInitialsOfCustomer())
        }
    }

    fun onProceedButtonClicked() {
        Log.i(TAG,"ProceedButton clicked")
        validateHeaderFieldsAndProceed()
    }

    private fun validateHeaderFieldsAndProceed() {
        //TOdo validate field -
        /** Insert Sales into the LDB and then fetch it from LDB to get the automatically generated
         * HeaderId that need to be passed to SalesDetail Fragment
         * */
        // create SalesHeader object
        var salesHeader = SalesHeaders(0, _receivedDate.value!!, _salesDate.value!!, _supplier.value!!, _noOfBox.value!!, _transportMedium.value!!, if(_transportDetail.value.isNullOrEmpty())  "No Value" else _transportDetail.value!!)
        viewModelScope.launch(Dispatchers.IO) {
            val headerId = salesUseCase.addSalesHeader(salesHeader)
            Log.i(TAG, "newLy entered Sales Header row id is $headerId")
            salesHeader = salesUseCase.getSalesHeaderForId(headerId)
            withContext(Dispatchers.Main) {
                _moveToSalesEntry.postValue(salesHeader)
            }
        }
    }

}