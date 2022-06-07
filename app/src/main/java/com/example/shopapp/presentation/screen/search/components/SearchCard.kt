package com.example.shopapp.presentation.screen.search.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.example.shopapp.presentation.navigation.NavigationRouter
import com.example.shopapp.presentation.screen.components.navigate
import com.example.shopapp.repository.remote.models.ProductDto
import com.example.shopapp.ui.theme.AppTheme


@Composable
fun SearchCard(product: ProductDto, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                navController.navigate(
                    NavigationRouter.Detailed.route,
                    bundleOf("PRODUCT" to product)
                )
            },
        backgroundColor = AppTheme.extendedColors.cardBackgroundColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SubcomposeAsyncImage(
                model = product.link,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.scale(0.5f)
                    )
                },
                modifier = Modifier
                    .scale(3f)
                    .padding(start = 15.dp, top = 15.dp),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(60.dp))
            Column {
                Text(text = product.price, color = AppTheme.textColors.primaryTextColor)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = product.name,
                    maxLines = 2,
                    overflow = TextOverflow.Clip,
                    color = AppTheme.textColors.primaryTextColor
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
        Text(text = product.category, color = AppTheme.textColors.primaryTextColor)
        Text(text = product.brand, color = AppTheme.textColors.primaryTextColor)
    }
}

