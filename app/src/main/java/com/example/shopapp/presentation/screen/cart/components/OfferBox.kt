package com.example.shopapp.presentation.screen.cart.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shopapp.domain.CartViewModel
import com.example.shopapp.repository.local.entity.ProductEntity
import com.example.shopapp.ui.theme.AppTheme


@Composable
fun OfferBox(cartViewModel: CartViewModel) {

    val fullPrice by remember { mutableStateOf(cartViewModel.calculateFullPrice()) }

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp)
        ) {
            Column {
                Row {
                    Text(text = "Ваша корзина", color = AppTheme.textColors.primaryTextColor)
                    Spacer(modifier = Modifier.fillMaxWidth(0.53f))
                    Text(
                        text = "${cartViewModel.getCartSize()} товар(a)",
                        color = AppTheme.textColors.primaryTextColor
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(text = "Товары", color = AppTheme.textColors.primaryTextColor)
                    Spacer(modifier = Modifier.fillMaxWidth(0.6f))
                    Text(text = "$fullPrice Р", color = AppTheme.textColors.primaryTextColor)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(text = "Баллы", color = AppTheme.textColors.primaryTextColor)
                    Spacer(modifier = Modifier.fillMaxWidth(0.6f))
                    Text(text = "${fullPrice * 0.1}", color = AppTheme.textColors.primaryTextColor)
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
                    Text(text = "Общая стоимость", color = AppTheme.textColors.primaryTextColor)
                    Spacer(modifier = Modifier.fillMaxWidth(0.5f))
                    Text(
                        text = "${fullPrice - fullPrice * 0.1} P",
                        color = AppTheme.textColors.primaryTextColor
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {/*TODO*/ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = AppTheme.extendedColors.buttonColor,
                        contentColor = AppTheme.textColors.primaryButtonText
                    )
                ) {
                    Text(text = "Перейти к оформлению")
                }
            }
        }
    }
}