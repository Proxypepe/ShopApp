package com.example.shopapp.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun CartScreen() {
    Column {
        Box {
            Text(text="Корзина пуста")
            Text(text="ла ла ла")


        }
    }
}

@Preview
@Composable
fun CartScreenPreview() {
    CartScreen()
}
