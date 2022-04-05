package com.example.shopapp.repository.remote.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommentDto(
    val id: Int = 0,
    val comment: String,
    val product: ProductDto,
    val user: UserDto
) : Parcelable
