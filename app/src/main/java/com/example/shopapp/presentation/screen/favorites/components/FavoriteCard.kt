package com.example.shopapp.presentation.screen.favorites.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopapp.R
import com.example.shopapp.repository.local.entity.ProductEntity


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