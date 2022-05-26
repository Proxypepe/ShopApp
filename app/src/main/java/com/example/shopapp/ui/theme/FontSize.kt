package com.example.shopapp.ui.theme

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

sealed class FontSize(val size: TextUnit) {
    object H1: FontSize(24.sp)
    object H2: FontSize(22.sp)
    object H3: FontSize(20.sp)
    object H4: FontSize(18.sp)
    object H5: FontSize(16.sp)
    object H6: FontSize(14.sp)

    object Common: FontSize(14.sp)
    object Middle: FontSize(12.sp)
    object Little: FontSize(10.sp)
}
