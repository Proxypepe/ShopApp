package com.example.shopapp.ui.theme

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

sealed class FontSize(val size: TextUnit) {
    object Common: FontSize(14.sp)
    object Middle: FontSize(12.sp)
    object Little: FontSize(10.sp)
}
