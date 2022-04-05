package com.example.shopapp.repository.remote.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShoppingBagDto (
    val bagId: Long = 0,
    val user_id: Int,
    val products: List<ProductDto> = emptyList(),
    val amount: Int,
    val price: Int
        ) : Parcelable