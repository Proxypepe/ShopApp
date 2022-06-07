package com.example.shopapp.presentation.screen.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shopapp.R
import com.example.shopapp.domain.*
import com.example.shopapp.presentation.screen.cart.components.CartCard
import com.example.shopapp.presentation.screen.cart.components.EmptyCart
import com.example.shopapp.presentation.screen.cart.components.OfferBox
import com.example.shopapp.presentation.screen.components.RecommendCard
import com.example.shopapp.ui.theme.AppTheme


@Composable
fun CartScreen(
    cartViewModel: CartViewModel, mainPageViewModel: MainViewModel,
    loginViewModel: LoginViewModel, favoriteViewModel: FavoriteViewModel,
    navController: NavHostController
) {
    val products = cartViewModel.cart.collectAsState(initial = listOf())
    val recommendedProducts =
        mainPageViewModel.recommendedProducts.collectAsState(initial = emptyList()).value

    Column(
        modifier = Modifier.fillMaxSize().background(AppTheme.colors.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.primary)
                .height(60.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Корзина",
                style = AppTheme.typography.h1,
                color = AppTheme.textColors.headerTextColor
            )
        }
        LazyColumn {
            if (products.value.isEmpty()) {
                item {
                    Surface(
                        modifier = Modifier
                            .background(AppTheme.colors.background)
                    ) {
                        EmptyCart(loginViewModel.userData, navController)
                    }
                }
            } else {
                items(products.value) { product ->
                    CartCard(cartViewModel, favoriteViewModel, product)
                }
                item {
                    OfferBox(cartViewModel)
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = "Рекомендуем",
                    style = AppTheme.typography.body1,
                    color = AppTheme.textColors.primaryTextColor
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
            recommendedProducts.let {
                item {
                    LazyRow(modifier = Modifier.padding(start = 15.dp)) {
                        items(it) { product ->
                            RecommendCard(product, navController)
                        }
                    }
                }
            }
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_android_black_24dp),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}
