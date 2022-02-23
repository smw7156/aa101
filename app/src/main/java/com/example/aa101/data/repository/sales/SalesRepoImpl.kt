package com.example.aa101.data.repository.sales

import com.example.aa101.data.room.dao.SalesDetailDao
import com.example.aa101.data.room.dao.SalesHeaderDao
import com.example.aa101.data.room.model.SalesDetail
import com.example.aa101.data.room.model.SalesHeaders
import javax.inject.Inject

class SalesRepoImpl @Inject constructor(
    private val headerDao: SalesHeaderDao,
    private val detailDao: SalesDetailDao,
): SalesRepository {

    override suspend fun createHeader(header: SalesHeaders): Int =
        headerDao.addNewSalesHeader(header).toInt()

    override suspend fun addSaleInHeader(detail: SalesDetail): Int =
        detailDao.addNewSaleEntry(detail).toInt()

    override suspend fun getHeaderDetailForId(headerId: Int): SalesHeaders =
        headerDao.getSalesHeaderForId(headerId)

    override suspend fun getSaleDetailForId(salesId: Int): SalesDetail =
        detailDao.getSalesDetailForId(salesId)

    override suspend fun getSalesForHeader(headerId: Int): List<SalesDetail> =
        detailDao.getSalesForHeaderId(headerId)

}