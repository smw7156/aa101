package com.example.aa101.useCase

import com.example.aa101.data.repository.sales.SalesRepository
import javax.inject.Inject

class SalesUseCase @Inject constructor(
    val salesRepo: SalesRepository
) {

}