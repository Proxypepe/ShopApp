package com.example.shopapp.repository.remote.repository

import com.example.shopapp.repository.remote.api.ProductApi
import com.example.shopapp.repository.remote.models.ProductDto
import retrofit2.Response

class ProductRepository(private val productApi: ProductApi) {

    suspend fun getDetailById(id: Int): Response<ProductDto> = productApi.getDetailById(id)
    suspend fun getRangeProduct(offset: Int, limit: Int): Response<List<ProductDto>>
    = productApi.getRangeProduct(offset, limit)

}