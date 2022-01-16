package com.example.aa101.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddSupplierViewModel : ViewModel() {

    private var _supplierName = MutableLiveData<String>()
    val supplierName: LiveData<String> get() = _supplierName

    private var _partyTrademark = MutableLiveData<String>()
    val partyTrademark: LiveData<String> get() = _partyTrademark

    private var _supplierMobNo = MutableLiveData<String>()
    val supplierMobNo: LiveData<String> get() = _supplierMobNo

    private var _supplierEmail = MutableLiveData<String>()
    val supplierEmail: LiveData<String> get() = _supplierEmail

    private var _supplierAddress = MutableLiveData<String>()
    val supplierAddress: LiveData<String> get() = _supplierAddress

}