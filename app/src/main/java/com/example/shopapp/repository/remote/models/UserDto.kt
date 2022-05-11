package com.example.shopapp.repository.remote.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDto(
    val userId: Long = 0,
    val email: String,
    val password: String?,
    val role: String,

    val bags: List<ShoppingBagDto> = emptyList(),

    val comments: List<CommentDto> = emptyList(),

    val favorites: List<FavoriteDto> = emptyList()
) : Parcelable