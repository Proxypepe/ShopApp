package com.example.shopapp.presentation.screen.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import com.example.shopapp.R
import com.example.shopapp.presentation.screen.components.navigate
import com.example.shopapp.repository.remote.models.ProductDto


@Composable
fun SearchCard(product: ProductDto, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                navController.navigate("detailed", bundleOf("PRODUCT" to product))
            },
    ) {
        Row(modifier = Modifier
                .fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.ic_android_black_24dp),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = product.price)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = product.name,
                    maxLines = 2,
                    overflow = TextOverflow.Clip
                )
                Spacer(modifier = Modifier.height(5.dp))
                Tags(product)

            }
        }
    }
}

@Composable
fun Tags(product: ProductDto) {
    Column {
        Text(text = product.category)
        Text(text = product.brand)
    }
}

