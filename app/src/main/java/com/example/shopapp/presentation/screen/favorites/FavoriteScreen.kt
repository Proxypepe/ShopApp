package com.example.shopapp.presentation.screen.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.shopapp.domain.FavoriteViewModel
import com.example.shopapp.presentation.screen.favorites.components.EmptyFavorites
import com.example.shopapp.presentation.screen.favorites.components.FavoriteBlock

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FavoriteScreen(favoriteViewModel: FavoriteViewModel, navController: NavHostController) {
    val favorites = favoriteViewModel.favorites.value
    if (favorites.isEmpty()){
        EmptyFavorites(navController)
    } else {
        LazyColumn {
            item {
                val pair = favoriteViewModel.divideFavorites()
                val first = pair.first
                val second = pair.second
                for (i in 0 until first.size)
                    FavoriteBlock(first[i]?.product, second[i]?.product)
            }
        }
    }
}
