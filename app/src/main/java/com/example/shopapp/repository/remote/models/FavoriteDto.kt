package com.example.shopapp.repository.remote.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteDto(
    val id: Long = 0,
    val user: UserDto,
    val product: ProductDto
) : Parcelable
