package com.example.shopapp.presentation.screen.detailed

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

enum class DetailedState {
    RateProduct, WriteComment
}

data class DetailedCommentState(
    var advantages: MutableState<String>    = mutableStateOf(""),
    var disadvantages: MutableState<String> = mutableStateOf(""),
    var comment: MutableState<String>       = mutableStateOf("")
)
