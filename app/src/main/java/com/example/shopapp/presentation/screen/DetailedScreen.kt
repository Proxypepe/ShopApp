package com.example.shopapp.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopapp.R
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalPagerApi
@Composable
fun DetailedScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Title",
                )},
                navigationIcon = { IconButton(onClick = {}) {
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
                    IconButton(onClick = {/*TODO*/}) {
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
            LazyColumn(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.93f)
            ) {
                item {
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
                    TabScreen()
                }
            }
            Button(onClick = {/*TODO*/},
            modifier = Modifier.fillMaxWidth(0.9f).height(35.dp)){
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("В корзину  цена", fontSize = 14.sp)
                }
            }
        }
    }
}


@Preview
@Composable
@ExperimentalPagerApi
fun DetailedScreenPreview() {
    DetailedScreen()
}
