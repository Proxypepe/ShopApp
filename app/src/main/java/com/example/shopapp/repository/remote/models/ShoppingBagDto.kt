package com.example.shopapp.repository.remote.models

data class ShoppingBagDto (
    val bagId: Long = 0,
    val user_id: Int,
    val products: List<ProductDto> = emptyList(),
    val amount: Int,
    val price: Int
        )