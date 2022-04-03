package com.example.shopapp.repository.remote.models

data class FavoriteDto(
    val id: Long = 0,
    val user: UserDto,
    val product: ProductDto
)
