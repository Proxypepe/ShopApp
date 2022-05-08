package com.example.shopapp.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shopapp.R
import com.example.shopapp.domain.CartViewModel
import com.example.shopapp.domain.FavoriteViewModel
import com.example.shopapp.domain.MainViewModel
import com.example.shopapp.presentation.navigation.NavigationRouter
import com.example.shopapp.presentation.screen.components.TabScreen
import com.example.shopapp.repository.remote.models.ProductDto
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalPagerApi
@Composable
fun DetailedScreen(mainViewModel: MainViewModel, favoriteViewModel: FavoriteViewModel,
                   cartViewModel: CartViewModel,
                   product: ProductDto, navController: NavHostController) {
    val color = if (favoriteViewModel.contains(product))
        Color.Red
            else
        Color.White
    val favoriteTint  = remember {
        mutableStateOf(color)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = product.name, maxLines = 2, overflow = TextOverflow.Clip
                )},
                navigationIcon = { IconButton(onClick = {
                    //FIXME prev route adapt for search
                    navController.popBackStack(NavigationRouter.Home.route, inclusive = true)
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }},
                actions = {
                    IconButton(onClick = {/*TODO*/}) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {
                        // #TODO: fix me
                        favoriteViewModel.onFavoritesChange(product)
                        favoriteTint.value = if (favoriteViewModel.contains(product))
                            Color.Red
                        else
                            Color.White
                    }) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "favorite",
                            tint = favoriteTint.value
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate(NavigationRouter.Cart.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "ShoppingCart",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.93f)
            ) {
                    Box(modifier = Modifier
                        .padding(start = 45.dp, end = 45.dp)
                        .fillMaxWidth(),
                        contentAlignment = Alignment.Center) {
                        Column {
                            Image(
                                painter = painterResource(R.drawable.ic_android_black_24dp),
                                contentDescription = "",
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.padding(top=10.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(text = "Рейтинг: ${mainViewModel.calculateRating(product)}" )
                                Spacer(modifier = Modifier.fillMaxWidth(0.4f))
                                OutlinedButton(onClick = {
                                    /*TODO*/
                                }) {
                                    Text(text = "Отзывы - ${product.comments.size}")
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    TabScreen(product)
            }
            Button(onClick = {
                cartViewModel.addToCart(product)
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(35.dp)){
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("В корзину ${product.price}", fontSize = 14.sp)
                }
            }
        }
    }
}
