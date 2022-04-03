package com.example.shopapp.repository.remote.api

import com.example.shopapp.repository.remote.models.ShoppingBagDto
import retrofit2.http.Body
import retrofit2.http.POST

interface CartApi {

    @POST("./add")
    suspend fun createCart(@Body cart: ShoppingBagDto): ShoppingBagDto
}