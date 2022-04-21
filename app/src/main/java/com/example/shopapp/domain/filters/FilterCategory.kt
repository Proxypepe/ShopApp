package com.example.shopapp.domain.filters

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class FilterCategory (
    val name: String,
    var selected: MutableState<Boolean> = mutableStateOf(false)
)
