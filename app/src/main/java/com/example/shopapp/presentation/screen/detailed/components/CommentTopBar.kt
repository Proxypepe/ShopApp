package com.example.shopapp.presentation.screen.detailed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shopapp.domain.DetailedViewModel
import com.example.shopapp.presentation.navigation.NavigationRouter
import com.example.shopapp.ui.theme.AppTheme

@Composable
fun CommentTopBar(
    detailedViewModel: DetailedViewModel,
    navController: NavHostController
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(AppTheme.colors.primary)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.fillMaxWidth(0.35f))
                Text(
                    text = "Новый отзыв",
                    style = AppTheme.typography.h4,
                    color = Color.White,
                )

                Spacer(modifier = Modifier.fillMaxWidth(0.65f))

                IconButton(onClick = {
                    navController.navigate(NavigationRouter.CommentRead.route)
                    detailedViewModel.clear()
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "close",
                        tint = Color.White,
                        modifier = Modifier.scale(1.3f)
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column {
                Text (
                    text = "Товар:",
                    style = AppTheme.typography.h4,
                    color = AppTheme.textColors.primaryTextColor
                )

                Text (
                    text = detailedViewModel.currentProduct!!.name,
                    style = AppTheme.typography.subtitle1,
                    color = AppTheme.textColors.primaryTextColor
                )
            }
        }
        Divider(
            color = Color.Gray,
            thickness = 2.dp
        )
    }
}