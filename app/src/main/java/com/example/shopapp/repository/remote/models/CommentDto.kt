package com.example.shopapp.repository.remote.models

data class CommentDto(
    val id: Int = 0,
    val comment: String,
    val product: ProductDto,
    val user: UserDto
)
