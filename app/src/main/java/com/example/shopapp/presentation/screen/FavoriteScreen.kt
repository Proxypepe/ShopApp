package com.example.shopapp.presentation.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shopapp.R
import com.example.shopapp.domain.FavoriteViewModel
import com.example.shopapp.repository.local.entity.ProductEntity
import com.example.shopapp.repository.remote.models.ProductDto

@Composable
fun FavoriteScreen(favoriteViewModel: FavoriteViewModel, navController: NavHostController) {
    val favorites = favoriteViewModel.favorites?.collectAsState(initial = emptyList())?.value
    if (favorites == null || favorites.isEmpty()){
        EmptyFavorites(navController)
    } else {
        LazyColumn {
            items(favorites) { favorite ->
                favorite.product?.let { FavoriteCart(product = it) }
            }
        }
    }
}


@Composable
fun FavoriteCart(product: ProductEntity) {
    Column {
        Card(
            modifier = Modifier
                .size(140.dp, 200.dp)
                .padding(end = 10.dp)
            ,
            elevation = 5.dp,
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {
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
        Button(onClick = {

        }) {
            Text("В корзину")
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
