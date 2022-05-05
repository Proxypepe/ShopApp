package com.example.shopapp.domain.filters

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class CategoryButtonState(
    val route: String,
    val name: String,
    val width: Dp,
    val height: Dp,
) {
    object Category: CategoryButtonState(route = "Category", name = "Категория", width = 120.dp, height = 35.dp)
    object Brand: CategoryButtonState(route = "Brand", name = "Бренд", width = 100.dp, height = 35.dp)
    object Color: CategoryButtonState(route = "Color", name = "Цвет", width = 100.dp, height = 35.dp)
    object Manufacturer: CategoryButtonState(route = "Manufacturer", name = "Производитель", width = 150.dp, height = 35.dp)
    object Material: CategoryButtonState(route = "Material", name = "Материал", width = 120.dp, height = 35.dp)
    object Start: CategoryButtonState(route = "Start", name = "Страрт", width = 120.dp, height = 35.dp)
}