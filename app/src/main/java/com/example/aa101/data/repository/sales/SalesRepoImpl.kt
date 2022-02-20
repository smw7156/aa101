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

    override suspend fun createHeader(header: SalesHeaders) {
        TODO("Not yet implemented")
    }

    override suspend fun addSaleInHeader(detail: SalesDetail) {
        TODO("Not yet implemented")
    }
}