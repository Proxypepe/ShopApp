package com.example.shopapp.presentation.screen.cart.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.shopapp.R
import com.example.shopapp.domain.CartViewModel
import com.example.shopapp.domain.FavoriteViewModel
import com.example.shopapp.domain.LoginViewModel
import com.example.shopapp.presentation.navigation.NavigationRouter
import com.example.shopapp.repository.TypeConvertor
import com.example.shopapp.repository.local.entity.ProductEntity
import com.example.shopapp.repository.remote.models.UserDto
import com.example.shopapp.ui.theme.AppTheme
import kotlinx.coroutines.flow.StateFlow


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun EmptyCart(
    userData: StateFlow<UserDto>,
    navController: NavHostController,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(AppTheme.extendedColors.cardBackgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        Column {
            Text(
                text = "?????????????? ??????????",
                style = AppTheme.typography.body1,
                color = AppTheme.textColors.primaryTextColor
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "???? ???? ????",
                style = AppTheme.typography.body1,
                color = AppTheme.textColors.primaryTextColor
            )
            Spacer(modifier = Modifier.height(15.dp))
            if (userData.value.userId == 0L || userData.value.email == "") {
                Button(onClick = {
                    navController.navigate(NavigationRouter.SignIn.route)
                }) {
                    Text(
                        text = "??????????",
                        style = AppTheme.typography.body1,
                        color = AppTheme.textColors.primaryButtonText
                    )
                }
            }
        }
    }
}

@Composable
fun CartCard(
    cartViewModel: CartViewModel,
    favoriteViewModel: FavoriteViewModel,
    product: ProductEntity
) {
    var amount by remember { mutableStateOf(1) }
    var isRed by remember { mutableStateOf(value = favoriteViewModel.contains(product)) }
    val animatedColor by animateColorAsState(
        targetValue = if (isRed) Color.Red else AppTheme.extendedColors.iconColor,
        animationSpec = tween(
            durationMillis = 100,
            delayMillis = 100,
            easing = LinearEasing
        )
    )

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = AppTheme.extendedColors.cardBackgroundColor,
    ) {
        Column {
            Box(modifier = Modifier.padding(top = 10.dp, start = 15.dp)) {
                Row {
                    SubcomposeAsyncImage(
                        model = product.link,
                        loading = {
                            CircularProgressIndicator()
                        },
                        modifier = Modifier.scale(3f).padding(start = 15.dp, top = 15.dp),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(60.dp))
                    Column {
                        Text(
                            text = product.price,
                            style = AppTheme.typography.body1,
                            color = AppTheme.textColors.primaryTextColor
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                modifier = Modifier.size(30.dp, 30.dp),
                                painter = painterResource(id = R.drawable.crown_filled),
                                contentDescription = "crown_icon",
                                tint = AppTheme.extendedColors.iconColor
                            )
                            Spacer(modifier = Modifier.width(7.dp))
                            Text(
                                text = "10 % ??????????",
                                style = AppTheme.typography.body1,
                                color = AppTheme.textColors.primaryTextColor
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = product.name,
                            maxLines = 2,
                            overflow = TextOverflow.Clip,
                            style = AppTheme.typography.body1,
                            color = AppTheme.textColors.primaryTextColor
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
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
                        favoriteViewModel.onFavoritesChange(product)
                        isRed = !isRed
                    },
                    modifier = Modifier.width(130.dp),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "favorite",
                            tint = animatedColor
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(
                            text = "?? ??????????????????",
                            style = AppTheme.typography.body1,
                            color = AppTheme.textColors.primaryTextColor
                        )
                    }
                }
                Spacer(modifier = Modifier.width(7.dp))
                IconButton(
                    onClick = {
                        cartViewModel.deleteProductById(product.prod_id)
                    },
                    modifier = Modifier.width(100.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete",
                            tint = AppTheme.extendedColors.iconColor
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(
                            text = "??????????????",
                            style = AppTheme.typography.body1,
                            color = AppTheme.textColors.primaryTextColor
                        )
                    }
                }
                Spacer(modifier = Modifier.fillMaxWidth(0.45f))
                Text(
                    text = amount.toString(),
                    style = AppTheme.typography.body1,
                    color = AppTheme.extendedColors.iconColor
                )
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(
                    onClick = {
                        amount++
                    },
                    modifier = Modifier.width(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowUp,
                        contentDescription = "increase",
                        tint = AppTheme.textColors.primaryButtonText
                    )
                }
                Spacer(modifier = Modifier.width(3.dp))
                IconButton(
                    onClick = {
                        amount--
                        if (amount == 0)
                            cartViewModel.deleteProductById(product.prod_id)
                    },
                    modifier = Modifier.width(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = "reduce",
                        tint = AppTheme.textColors.primaryButtonText
                    )
                }
            }
        }
    }
}
