package com.example.shopapp.presentation.screen.cart.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun OfferBox() {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp)
        ) {
            Column {
                Row {
                    Text(text = "Ваша корзина")
                    Spacer(modifier = Modifier.fillMaxWidth(0.53f))
                    Text(text = "1 товар")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(text = "Товары")
                    Spacer(modifier = Modifier.fillMaxWidth(0.6f))
                    Text(text = "34 990 Р")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(text = "Скидка")
                    Spacer(modifier = Modifier.fillMaxWidth(0.6f))
                    Text(text = "- 8000 Р")
                }
            }
        }
        Divider(color = Color.Gray, thickness = 1.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp)
        ) {
            Column {
                Row {
                    Text(text = "Общая стоимость")
                    Spacer(modifier = Modifier.fillMaxWidth(0.5f))
                    Text(text = "2222P")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {/*TODO*/ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Перейти к оформлению")
                }
            }
        }
    }
}