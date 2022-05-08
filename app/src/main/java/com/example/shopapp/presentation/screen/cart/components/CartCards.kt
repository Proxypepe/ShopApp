package com.example.shopapp.presentation.screen.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopapp.R
import com.example.shopapp.domain.CartViewModel
import com.example.shopapp.domain.FavoriteViewModel
import com.example.shopapp.repository.local.entity.ProductEntity


@Composable
fun EmptyCart(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        contentAlignment = Alignment.Center,
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        Column {
            Text(text = "Корзина пуста")
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "ла ла ла")
            Spacer(modifier = Modifier.height(15.dp))
            Button(onClick = {/*TODO*/}) {
                Text(text = "Войти")
            }
        }
    }
}

@Composable
fun CartCard(cartViewModel: CartViewModel, favoriteViewModel: FavoriteViewModel, product: ProductEntity) {
    var amount by remember { mutableStateOf(1) }
    Card(modifier = Modifier.fillMaxWidth()) {
        Column {
            Box(modifier = Modifier.padding(top = 10.dp, start = 15.dp)) {
                Row {
                    Image(painter = painterResource(id = R.drawable.ic_android_black_24dp),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(text = product.price)
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            horizontalArrangement= Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,

                            ) {
                            Icon(
                                modifier = Modifier.size(30.dp, 30.dp),
                                painter = painterResource(id = R.drawable.crown_filled),
                                contentDescription = "crown_icon"
                            )
                            Spacer(modifier = Modifier.width(7.dp))
                            Text(text = "10 % баллы")
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = product.name,
                            maxLines = 2,
                            overflow = TextOverflow.Clip)
                    }
                }
            }
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = {
                        favoriteViewModel.addFavorite(product)
                    },
                    modifier = Modifier.width(130.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Icon(imageVector = Icons.Default.Favorite,
                            contentDescription = "favorite"
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(text = "В избранное")
                    }
                }
                Spacer(modifier = Modifier.width(7.dp))
                IconButton(
                    onClick = {
                        cartViewModel.deleteProductById(product.prod_id)
                    },
                    modifier = Modifier.width(100.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Icon(imageVector = Icons.Default.Delete,
                            contentDescription = "delete"
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(text = "Удалить")
                    }
                }
                Spacer(modifier = Modifier.fillMaxWidth(0.45f))
                Text(text = amount.toString(), fontSize = 14.sp)
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(onClick = { amount++ },
                    modifier = Modifier.width(30.dp)) {
                    Icon(imageVector = Icons.Outlined.KeyboardArrowUp,
                        contentDescription = "add")
                }
                Spacer(modifier = Modifier.width(3.dp))
                IconButton(onClick = { amount-- },
                    modifier = Modifier.width(30.dp)) {
                    Icon(imageVector = Icons.Outlined.KeyboardArrowUp,
                        contentDescription = "reduce")
                }
            }
        }
    }
}