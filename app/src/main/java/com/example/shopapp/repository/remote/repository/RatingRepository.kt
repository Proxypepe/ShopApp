package com.example.shopapp.repository.remote.repository

import com.example.shopapp.repository.remote.api.RatingApi
import com.example.shopapp.repository.remote.models.RatingDto
import retrofit2.Response

class RatingRepository(private val ratingApi: RatingApi) {

    suspend fun createRating(rating: RatingDto): Response<RatingDto>
     = ratingApi.createRating(rating)
}