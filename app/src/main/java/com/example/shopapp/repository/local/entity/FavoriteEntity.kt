package com.example.shopapp.repository.local.entity

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey
    @NotNull @NonNull
    val product_id: Int,
    @Nullable
    @Embedded
    val product: ProductEntity?
)