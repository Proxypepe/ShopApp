package com.example.shopapp.presentation.screen.components

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import com.example.shopapp.R
import com.example.shopapp.presentation.navigation.NavigationRouter
import com.example.shopapp.repository.remote.models.ProductDto



@Composable
fun RecommendCard(product: ProductDto, navController: NavHostController){
    Card(
        modifier = Modifier
            .size(140.dp, 200.dp).padding(end=10.dp)
            .clickable {
                navController.navigate(NavigationRouter.Detailed.route, bundleOf("PRODUCT" to product))
            },
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
}

fun NavHostController?.navigate(route: String, params: Bundle?, builder: NavOptionsBuilder.() -> Unit = {}) {
    this?.currentBackStackEntry?.arguments?.putAll(params)

    this?.navigate(route, builder)
}
