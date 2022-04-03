package com.example.shopapp.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import com.example.shopapp.domain.MainViewModel
import com.example.shopapp.domain.MainViewModelFactory
import com.example.shopapp.presentation.navigation.AppNavigation
import com.example.shopapp.repository.ShoppingAppApplication
import com.example.shopapp.ui.theme.ShopAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as ShoppingAppApplication).productRepository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        mainViewModel.getRecommendedProduct()
        setContent {
            ShopAppTheme {
                AppNavigation(
                    mainPageViewModel = mainViewModel
                )
            }
        }
    }
}
