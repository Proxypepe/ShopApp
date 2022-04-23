package com.example.shopapp.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shopapp.R
import com.example.shopapp.domain.FavoriteViewModel
import com.example.shopapp.repository.local.entity.ProductEntity

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

@Composable
fun FavoriteBlock(productLeft: ProductEntity?, productRight: ProductEntity?) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxWidth(0.5f)) {
            productRight?.let {
                FavoriteCart(productRight)
            }
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            productLeft?.let {
                FavoriteCart(productLeft)
            }
        }
    }
}


@Composable
fun FavoriteCart(product: ProductEntity) {
    Card(
        elevation = 8.dp,
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(10.dp)
    ) {
        Column {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                contentAlignment = Alignment.Center
            ){
                Column {
                    Image(painter = painterResource(id = R.drawable.ic_android_black_24dp),
                        contentDescription = ""
                    )
                    Text(
                        text = product.price, fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = product.name, maxLines = 2, overflow = TextOverflow.Ellipsis,
                        fontSize = 12.sp
                    )
                }
            }
            Box(modifier = Modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {

                }) {
                    Text("В корзину")
                }
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
            Text(text = "В Избранном пока ничего нет",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Добавьте товар в избранное",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(7.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    navController.navigate("home")
                }) {
                    Text(text = "На главную")
                }
            }
        }
    }
}
