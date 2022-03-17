package com.example.aa101.screen

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aa101.data.room.model.Suppliers
import com.example.aa101.useCase.SupplierUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSupplierViewModel @Inject constructor(
    private val supplierUseCase: SupplierUseCase
): ViewModel() {

    private val TAG = "AddSupplierViewModel"

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

    private var _isSupplierAdded = MutableLiveData<Boolean>(false)
    val isSupplierAdded: LiveData<Boolean> get() = _isSupplierAdded

    fun setSupplierName(name: String) = _supplierName.postValue(name)
    fun setSupplierTradeMark(tradeMark: String) = _partyTrademark.postValue(tradeMark)
    fun setSupplierMobile(value: String) = _supplierMobNo.postValue(value)
    fun setSupplierEmail(value: String) = _supplierEmail.postValue(value)
    fun setSupplierAddress(value: String) = _supplierAddress.postValue(value)

    fun isTradeMarkValid(partyTradeMark: String): Boolean {
        return false
    }

    fun onAddClicked() {
        if (_supplierName.value.isNullOrEmpty()) {
            Log.e(TAG , "Supplier name is empty")
        }
        if (_partyTrademark.value.isNullOrEmpty()) {
            Log.e(TAG , "Supplier trademark is empty")
        }
        if (_supplierMobNo.value.isNullOrEmpty()) {
            Log.e(TAG , "Supplier Mob no. is empty")
        }
        if (_supplierEmail.value.isNullOrEmpty()) {
            Log.e(TAG , "Supplier email is empty")
        }

        Log.i(TAG, "adding supplier in DB")
        viewModelScope.launch(Dispatchers.IO) {
            if (supplierUseCase.addSuppliers(
                Suppliers(
                    supplierName = _supplierName.value.toString(),
                    supplierTradeMark = _partyTrademark.value.toString(),
                    supplierMobileNo = _supplierMobNo.value.toString(),
                    supplierEmail = _supplierEmail.value.toString(),
                    supplierAddress = _supplierAddress.value.toString(),
                )
            ) > 0 ) {
                _isSupplierAdded.postValue(true)
            }
        }
    }

    fun resetSupplierAdded() = _isSupplierAdded.postValue(false)

}