package com.example.shopapp.presentation.screen.detailed.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import com.example.shopapp.presentation.navigation.NavigationRouter
import com.example.shopapp.repository.remote.models.ProductDto
import com.example.shopapp.ui.theme.AppTheme

@Composable
fun DetailedTopBar(
    product: ProductDto,
    isContains: (product: ProductDto) -> Boolean,
    onFavoritesChange: (product: ProductDto) -> Unit,
    navController: NavHostController
) {
    var isRed by remember { mutableStateOf(value = isContains(product)) }
    val animatedColor by animateColorAsState(
        if (isRed) Color.Red else Color.White,
        animationSpec = tween(
            durationMillis = 100,
            delayMillis = 100,
            easing = LinearEasing
        )
    )

    TopAppBar(
        title = {
            Text(
                text = product.name,
                style = AppTheme.typography.h4,
                maxLines = 2,
                overflow = TextOverflow.Clip
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }
        },
        actions = {
            IconButton(onClick = {/*TODO*/}) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = Color.White
                )
            }
            IconButton(onClick = {
                onFavoritesChange(product)
                isRed = !isRed
            }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "favorite",
                    tint = animatedColor
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
