package com.example.shopapp.repository.remote.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RatingDto(
    val id: Int = 0,
    val rating: Float,

    val product: ProductDto,
    val user: UserDto,
) : Parcelable