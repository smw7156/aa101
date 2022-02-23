package com.example.aa101.useCase

import com.example.aa101.data.repository.sales.SalesRepository
import com.example.aa101.data.room.model.SalesDetail
import com.example.aa101.data.room.model.SalesHeaders
import javax.inject.Inject

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

}