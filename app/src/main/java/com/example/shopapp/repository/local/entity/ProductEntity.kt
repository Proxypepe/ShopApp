package com.example.shopapp.repository.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "products")
data class ProductEntity (
    @PrimaryKey(autoGenerate = true)
    val id_: Int,
    @NotNull @NonNull
    val prod_id: Int,
    @NotNull @NonNull
    val name: String,
    @NotNull @NonNull
    val description: String,
    @NotNull @NonNull
    val price: String,
    @NotNull @NonNull
    val stock: Int,
    @NotNull @NonNull
    val category: String,
    @NotNull @NonNull
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
)