package com.example.shopapp.presentation.screen.detailed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.example.shopapp.R
import com.example.shopapp.domain.CartViewModel
import com.example.shopapp.domain.DetailedViewModel
import com.example.shopapp.domain.FavoriteViewModel
import com.example.shopapp.presentation.navigation.NavigationRouter
import com.example.shopapp.presentation.screen.detailed.components.DetailedTopBar
import com.example.shopapp.presentation.screen.detailed.components.TabScreen
import com.example.shopapp.presentation.screen.detailed.components.rataing.CustomRatingBar
import com.example.shopapp.repository.remote.models.ProductDto
import com.example.shopapp.ui.theme.AppTheme
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalPagerApi
@Composable
fun DetailedScreen(
    detailedViewModel: DetailedViewModel, favoriteViewModel: FavoriteViewModel,
    cartViewModel: CartViewModel,
    product: ProductDto, navController: NavHostController
) {
    val rating by remember { mutableStateOf(detailedViewModel.calculateRating(product)) }

    Scaffold(
        topBar = {
            DetailedTopBar(
                product = product,
                isContains = favoriteViewModel::contains,
                onFavoritesChange = favoriteViewModel::onFavoritesChange,
                navController = navController
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.90f)
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        SubcomposeAsyncImage(
                            model = product.link,
                            loading = {
                                CircularProgressIndicator()
                            },
                            modifier = Modifier.scale(4f).padding(start = 65.dp, top = 25.dp),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.height(100.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Рейтинг: $rating",
                                    color = AppTheme.textColors.primaryTextColor
                                )

                                CustomRatingBar(
                                    value = rating,
                                    onValueChange = {},
                                    onRatingChanged = {}
                                )
                            }
                            Spacer(modifier = Modifier.fillMaxWidth(0.4f))
                            OutlinedButton(
                                onClick = {
                                    detailedViewModel.currentProduct = product
                                    navController.navigate(NavigationRouter.CommentRead.route)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = AppTheme.extendedColors.buttonColor,
                                    contentColor = AppTheme.textColors.primaryButtonText
                                )
                            ) {
                                Text(
                                    text = "Отзывы - ${product.comments.size}"
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
                TabScreen(product)
            }
            Button(
                onClick = {
                    cartViewModel.addToCart(product)
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = AppTheme.extendedColors.buttonColor,
                    contentColor = AppTheme.textColors.primaryButtonText
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "В корзину",
                        style = AppTheme.typography.body2
                    )
                    Text(
                        text = product.price,
                        style = AppTheme.typography.body2
                    )
                }
            }
        }
    }
}
