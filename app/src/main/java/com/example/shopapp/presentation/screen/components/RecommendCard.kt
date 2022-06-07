package com.example.shopapp.presentation.screen.components

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.shopapp.R
import com.example.shopapp.presentation.navigation.NavigationRouter
import com.example.shopapp.repository.remote.models.ProductDto
import com.example.shopapp.ui.theme.AppTheme


@Composable
fun RecommendCard(product: ProductDto, navController: NavHostController) {
    Card(
        modifier = Modifier
            .size(140.dp, 200.dp)
            .padding(end = 10.dp)
            .clickable {
                navController.navigate(
                    NavigationRouter.Detailed.route,
                    bundleOf("PRODUCT" to product)
                )
            },
        backgroundColor = AppTheme.extendedColors.cardBackgroundColor,
        elevation = AppTheme.extendedColors.elevation,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            SubcomposeAsyncImage(
                model = product.link,
                loading = {
                    CircularProgressIndicator(modifier = Modifier.scale(0.5f))
                },
                contentDescription = "",
                modifier = Modifier.scale(4f)
            )
            Spacer(modifier = Modifier.height(55.dp))
            Text(
                text = product.price,
                fontWeight = FontWeight.Bold,
                style = AppTheme.typography.body1,
                color = AppTheme.textColors.primaryTextColor
            )
            Text(
                text = product.name, maxLines = 2, overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp, color = AppTheme.textColors.primaryTextColor
            )
        }
    }
}

fun NavHostController?.navigate(
    route: String,
    params: Bundle?,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    this?.currentBackStackEntry?.arguments?.putAll(params)

    this?.navigate(route, builder)
}
