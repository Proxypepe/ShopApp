package com.example.shopapp.presentation.screen.favorites.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.shopapp.domain.CartViewModel
import com.example.shopapp.domain.FavoriteViewModel
import com.example.shopapp.repository.local.entity.ProductEntity
import com.example.shopapp.ui.theme.AppTheme


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
            .padding(10.dp),
        backgroundColor = AppTheme.extendedColors.cardBackgroundColor,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            SubcomposeAsyncImage(
                model = product.link,
                loading = {
                    CircularProgressIndicator()
                },
                modifier = Modifier.scale(5f),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(70.dp))
            Text(
                text = product.price,
                fontWeight = FontWeight.Bold,
                color = AppTheme.textColors.primaryTextColor,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 5.dp)
            )
            Text(
                text = product.name, maxLines = 2, overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp, color = AppTheme.textColors.primaryTextColor,
                modifier = Modifier.padding(start = 5.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        cartViewModel.addToCart(product)

                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = AppTheme.extendedColors.buttonColor,
                        contentColor = AppTheme.textColors.primaryButtonText
                    ),
                ) {
                    Text("В корзину")
                }
            }
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
