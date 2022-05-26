package com.example.shopapp.presentation.screen.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shopapp.domain.CartViewModel
import com.example.shopapp.domain.FavoriteViewModel
import com.example.shopapp.presentation.screen.favorites.components.EmptyFavorites
import com.example.shopapp.presentation.screen.favorites.components.FavoriteBlock
import com.example.shopapp.ui.theme.AppTheme

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FavoriteScreen(
    cartViewModel: CartViewModel,
    favoriteViewModel: FavoriteViewModel,
    navController: NavHostController
) {
    val favorites = favoriteViewModel.favorites.value
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.primary)
                .height(60.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Избранные",
                style = AppTheme.typography.h1,
                color = Color.White
            )
        }

        if (favorites.isEmpty()) {
            Spacer(modifier = Modifier.fillMaxHeight(0.5f))
            EmptyFavorites(navController)
        } else {
            LazyColumn {
                item {
                    val pair = favoriteViewModel.divideFavorites()
                    val first = pair.first
                    val second = pair.second
                    for (i in 0 until first.size)
                        FavoriteBlock(
                            cartViewModel = cartViewModel,
                            favoriteViewModel = favoriteViewModel,
                            productLeft = first[i]?.product,
                            productRight = second[i]?.product
                        )
                }
            }
        }
    }
}
