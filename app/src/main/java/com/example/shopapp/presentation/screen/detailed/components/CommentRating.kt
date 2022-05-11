package com.example.shopapp.presentation.screen.detailed.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shopapp.domain.DetailedViewModel
import com.example.shopapp.presentation.screen.detailed.components.rataing.CustomRatingBar
import com.example.shopapp.presentation.screen.detailed.components.rataing.RatingBarConfig
import com.example.shopapp.ui.theme.AppTheme


@Composable
fun RateProduct(
    detailedViewModel: DetailedViewModel, navController: NavHostController,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CommentTopBar(detailedViewModel, navController)

        Spacer(modifier = Modifier.fillMaxHeight(0.4f))

        Column {
            CustomRatingBar(
                modifier = Modifier,
                value = detailedViewModel.rating(),
                config = RatingBarConfig().apply{
                    size = 50.dp
                },
                onValueChange = detailedViewModel::updateRating,
                onRatingChanged = { }
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Оцените товар!",
                style = AppTheme.typography.h3,
                color = AppTheme.textColors.primaryTextColor
            )
        }
    }
}
