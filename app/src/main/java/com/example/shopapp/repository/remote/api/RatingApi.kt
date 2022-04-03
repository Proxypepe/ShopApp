package com.example.shopapp.repository.remote.api

import com.example.shopapp.repository.remote.models.RatingDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RatingApi {

    @POST("./add")
    suspend fun createRating(@Body rating: RatingDto): Response<RatingDto>

}