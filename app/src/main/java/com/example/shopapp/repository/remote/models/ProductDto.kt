package com.example.shopapp.repository.remote.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProductDto(
    val prod_id: Int = 0,
    val name: String,
    val description: String?,
    val price: String,
    val stock: Int,
    val category: String,
    val brand: String,
    val shell_type: String?,
    val top_deck: String?,
    val top_material: String?,
    val back_deck: String?,
    val neck_material: String?,
    val overlay: String?,
    val strings: String?,
    val neck_attachment: String?,
    val mensura: String?,
    val neck_width: String?,
    val color: String?,
    val tailpiece: String?,
    val produced: String?,
    val cutout: String?,
    val varnish: String?,
    val form: String?,
    val specials: String?,
    val lads: String?,
    val link: String?,

    val bags: List<ShoppingBagDto> = emptyList(),
    val comments: List<CommentDto> = emptyList(),
    val favoriteBy: List<FavoriteDto> = emptyList(),
) : Parcelable
