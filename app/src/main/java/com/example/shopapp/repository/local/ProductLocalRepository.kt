package com.example.shopapp.repository.local

import com.example.shopapp.repository.local.dao.ProductDao
import com.example.shopapp.repository.local.entity.ProductEntity

class ProductLocalRepository(private val productDao: ProductDao){
    fun getProducts()  = productDao.getProducts()

    suspend fun insertProduct(product: ProductEntity) = productDao.insertProduct(product)

    suspend fun deleteProduct(product: ProductEntity) = productDao.deleteProduct(product)
}