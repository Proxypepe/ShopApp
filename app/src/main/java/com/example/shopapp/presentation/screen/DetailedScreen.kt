package com.example.shopapp.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopapp.R
import com.example.shopapp.domain.MainViewModel
import com.example.shopapp.repository.remote.models.ProductDto
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalPagerApi
@Composable
fun DetailedScreen(mainPageViewModel: MainViewModel, product: ProductDto, navController: NavController?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = product.name, maxLines = 2, overflow = TextOverflow.Clip
                )},
                navigationIcon = { IconButton(onClick = {
                    //FIXME prev route adapt for search
                    navController?.popBackStack("home", inclusive = true)
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
                        navController?.navigate("cart")
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
                                Text(text="Рейтинг: ")
                                Spacer(modifier = Modifier.fillMaxWidth(0.5f))
                                OutlinedButton(onClick = { /*TODO*/ }) {
                                    Text(text="Отзывы - 5")
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    TabScreen(product)
            }
            Button(onClick = {
                mainPageViewModel.addToCart(product)
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
