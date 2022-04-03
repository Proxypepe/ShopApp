package com.example.shopapp.repository.remote.api

import com.example.shopapp.repository.remote.models.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("./detail/{id}")
    suspend fun getDetailById(@Path("id")id :Int): ProductDto

    @GET("./range")
    suspend fun getRangeProduct(@Query("offset")offset: Int, @Query("limit")limit: Int)

}