package com.example.shopapp.presentation.screen.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shopapp.R
import com.example.shopapp.domain.MainViewModel
import com.example.shopapp.presentation.screen.components.LoadProgress
import com.example.shopapp.presentation.screen.components.RecommendCard
import com.example.shopapp.presentation.screen.main.components.BrandCard
import com.example.shopapp.ui.theme.AppTheme


@Composable
fun MainPage(mainPageViewModel: MainViewModel?, navController: NavHostController) {

    val products =
        mainPageViewModel?.recommendedProducts?.collectAsState(initial = emptyList())?.value

    val loading = mainPageViewModel?.loading?.collectAsState(false)?.value
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.primary)
                .height(60.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Главное",
                style = AppTheme.typography.h1,
                color = Color.White
            )
        }
        Box(
            modifier = Modifier.padding(10.dp)
        ) {
            LazyColumn {
                item {
                    Spacer(modifier = Modifier.padding(top = 20.dp, start = 25.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Your Feed", fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.fillMaxWidth(0.8f))
                        Box(
                            modifier = Modifier
                                .border(
                                    width = 2.dp,
                                    color = AppTheme.extendedColors.outlineColor.copy(alpha = 0.6f),
                                    shape = RoundedCornerShape(32.dp)
                                )
                                .padding(5.dp),
                        ) {
                            BadgedBox(
                                badge = {
                                    Badge {
                                        Text(text = "")
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Notifications,
                                    contentDescription = "Notifications"
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 5.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_android_black_24dp),
                            contentDescription = "",
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    val values: List<Int> = listOf(1, 2, 3, 4)
                    Text(
                        text = "Ваши рекомендации",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Основано на вашем поиске",
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        if (products != null)
                            items(products) { value ->
                                RecommendCard(value, navController)
                            } else {
                            item {
                                if (loading != null) {
                                    LoadProgress(loading)
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(
                        text = "Популярные бренды",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        items(values) {
                            BrandCard()
                        }
                    }
                }
            }
        }
    }
}
