package com.example.shopapp.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopapp.R
import com.example.shopapp.repository.local.entity.ProductEntity


@Composable
fun CartScreen(products: List<ProductEntity>?) {
    LazyColumn {
        item {
            Text(text = "Корзина")
            if(products == null)
            {
                EmptyCart()
            }
            else {
                ShowCart(products)
                OfferBox()
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Рекомендуем")
            Spacer(modifier = Modifier.height(5.dp))
            LazyRow {
                items(4) {
                    RecommendCard(1)
                }
            }
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
fun ShowCart(products: List<ProductEntity>) {
    LazyColumn {
        items(products) { product ->
            CartCard(product.name, product.price, product.link)
        }
    }
}

@Composable
fun CartCard(name: String, price: String, link: String?) {
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
                        Text(text = price)
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
                            text = name,
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
                    onClick = { /*TODO*/ },
                    modifier = Modifier.width(90.dp)) {
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
                Spacer(modifier = Modifier.fillMaxWidth(0.6f))
                Text(text = amount.toString(), fontSize = 14.sp)
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(onClick = { amount++ }) {
                    Icon(imageVector = Icons.Outlined.KeyboardArrowUp,
                        contentDescription = "add")
                }
                Spacer(modifier = Modifier.width(3.dp))
                IconButton(onClick = { amount-- }) {
                    Icon(imageVector = Icons.Outlined.KeyboardArrowUp,
                        contentDescription = "reduce")
                }
            }
        }

    }
}

@Composable
fun OfferBox() {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp)
        ) {
            Column {
                Row {
                    Text(text = "Ваша корзина")
                    Spacer(modifier = Modifier.fillMaxWidth(0.53f))
                    Text(text = "1 товар")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(text = "Товары")
                    Spacer(modifier = Modifier.fillMaxWidth(0.6f))
                    Text(text = "34 990 Р")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(text = "Скидка")
                    Spacer(modifier = Modifier.fillMaxWidth(0.6f))
                    Text(text = "- 8000 Р")
                }
            }
        }
        Divider(color = Color.Gray, thickness = 1.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp)
        ) {
            Column {
                Row {
                    Text(text = "Общая стоимость")
                    Spacer(modifier = Modifier.fillMaxWidth(0.5f))
                    Text(text = "2222P")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {/*TODO*/ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Перейти к оформлению")
                }
            }
        }
    }
}

@Preview
@Composable
fun CartScreenPreview() {
    CartScreen(null)
}

@Preview
@Composable
fun CartCardPreview() {
    CartCard("Some name", "Some price", null)
}

@Preview
@Composable
fun OfferBoxPreview() {
    OfferBox()
}
