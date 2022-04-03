package com.example.shopapp.repository.remote.models

data class UserResponseDto(
    val email: String,
    val role: String,
    val favorites: List<FavoriteDto> = emptyList(),
    val bags: List<ShoppingBagDto> = emptyList()
)
