package com.example.shopapp.repository.remote.repository

import com.example.shopapp.repository.remote.api.CartApi
import com.example.shopapp.repository.remote.models.ShoppingBagDto
import retrofit2.Response

class CartRepository(private val cartApi: CartApi) {

    suspend fun createCart(cart: ShoppingBagDto): Response<ShoppingBagDto> =
        cartApi.createCart(cart)
}