package com.example.shopapp.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import com.example.shopapp.domain.*
import com.example.shopapp.presentation.navigation.AppNavigation
import com.example.shopapp.repository.ShoppingAppApplication
import com.example.shopapp.ui.theme.ShopAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    private val cartViewModel: CartViewModel by viewModels {
        CartViewModelFactory((application as ShoppingAppApplication).localRepository)
    }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as ShoppingAppApplication).productRepository,
            cartViewModel)
    }

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        FavoriteViewModelFactory((application as ShoppingAppApplication).favoriteRepository,
            (application as ShoppingAppApplication).favoriteLocalRepository)
    }

    private fun initInfoLists() {
        mainViewModel.getRecommendedProduct()
        cartViewModel.getCart()
        favoriteViewModel.getFavorites()
    }

    override fun onStart() {
        super.onStart()
        initInfoLists()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopAppTheme {
                AppNavigation(
                    mainPageViewModel = mainViewModel,
                    favoriteViewModel = favoriteViewModel,
                    cartViewModel = cartViewModel
                )
            }
        }
    }
}
