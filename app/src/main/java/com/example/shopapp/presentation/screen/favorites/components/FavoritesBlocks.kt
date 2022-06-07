package com.example.shopapp.presentation.screen.favorites.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shopapp.domain.CartViewModel
import com.example.shopapp.domain.FavoriteViewModel
import com.example.shopapp.repository.local.entity.ProductEntity
import com.example.shopapp.ui.theme.AppTheme


@Composable
fun FavoriteBlock(
    cartViewModel: CartViewModel,
    favoriteViewModel: FavoriteViewModel,
    productLeft: ProductEntity?,
    productRight: ProductEntity?
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxWidth(0.5f)) {
            productRight?.let {
                FavoriteCart(cartViewModel, favoriteViewModel, productRight)
            }
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            productLeft?.let {
                FavoriteCart(cartViewModel, favoriteViewModel, productLeft)
            }
        }
    }
}


@Composable
fun EmptyFavorites(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "В Избранном пока ничего нет",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Добавьте товар в избранное",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(7.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        navController.navigate("home")
                    }, colors = ButtonDefaults.buttonColors(
                        backgroundColor = AppTheme.extendedColors.buttonColor,
                        contentColor = AppTheme.textColors.primaryButtonText
                    )
                ) {
                    Text(text = "На главную")
                }
            }
        }
    }
}

