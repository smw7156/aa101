package com.example.aa101.useCase

import com.example.aa101.data.repository.sales.SalesRepository
import com.example.aa101.data.room.model.SalesDetail
import com.example.aa101.data.room.model.SalesHeaders
import javax.inject.Inject
import kotlin.math.floor

class SalesUseCase @Inject constructor(
    private val salesRepo: SalesRepository
) {

    suspend fun addSalesHeader(headerDetail: SalesHeaders): Int =
        salesRepo.createHeader(headerDetail)

    suspend fun addSalesDetailInHeader(salesDetail: SalesDetail): Int =
        salesRepo.addSaleInHeader(salesDetail)

    suspend fun getSalesHeaderForId(headerId: Int): SalesHeaders =
        salesRepo.getHeaderDetailForId(headerId)


    suspend fun getSalesForId(salesId: Int): SalesDetail =
        salesRepo.getSaleDetailForId(salesId)

    suspend fun getSalesForHeader(headerId: Int): List<SalesDetail> =
        salesRepo.getSalesForHeader(headerId)

    fun calculateNetWeight(grossWt: Double) : Double {
        val lessWeight = ( floor(((grossWt-8)/10)+1) + floor(((grossWt-3.25)/10)+1) ) * 0.25
        return grossWt - lessWeight
    }
}