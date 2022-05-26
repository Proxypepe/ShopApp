package com.example.shopapp.presentation.screen.favorites.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopapp.R
import com.example.shopapp.domain.CartViewModel
import com.example.shopapp.domain.FavoriteViewModel
import com.example.shopapp.repository.local.entity.ProductEntity


@Composable
fun FavoriteCart(
    cartViewModel: CartViewModel,
    favoriteViewModel: FavoriteViewModel,
    product: ProductEntity
) {
//    var isRed by remember { mutableStateOf(value = favoriteViewModel.contains(product)) }
//    val animatedColor by animateColorAsState(
//        if (isRed) Color.Red else Color.White,
//        animationSpec = tween(
//            durationMillis = 100,
//            delayMillis = 100,
//            easing = LinearEasing
//        )
//    )
    
    Card(
        elevation = 8.dp,
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(10.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.ic_android_black_24dp),
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
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    cartViewModel.addToCart(product)
                }) {
                    Text("В корзину")
                }
//                Spacer(modifier = Modifier.width(5.dp))
//                IconButton(onClick = {
//                    isRed = !isRed
//                    favoriteViewModel.onFavoritesChange(product)
//                }) {
//                    Icon(
//                        imageVector = Icons.Default.Favorite,
//                        contentDescription = "favorite",
//                        tint = animatedColor
//                    )
//                }
            }
        }
    }
}