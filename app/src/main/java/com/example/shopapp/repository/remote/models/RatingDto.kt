package com.example.shopapp.repository.remote.models

data class RatingDto(
    val id: Int = 0,
    val rating: Float,

    val product: ProductDto,
    val user: UserDto,
)