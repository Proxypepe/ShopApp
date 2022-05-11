package com.example.shopapp.repository.remote.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommentDto(
    val comment: String?,
    val advantages: String?,
    val disadvantages: String?,
    val rating: Float,
    val commentedProduct: ProductDto?,
    val commentedByUser: UserDto?
) : Parcelable
