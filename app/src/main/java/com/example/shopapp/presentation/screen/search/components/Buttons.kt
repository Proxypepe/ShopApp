package com.example.shopapp.presentation.screen.search.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopapp.domain.SearchViewModel
import com.example.shopapp.domain.filters.CategoryButtonState

@Composable
fun FilterButtons(searchViewModel: SearchViewModel) {
    var expanded by remember { mutableStateOf(false) }
    var categoryRoute by remember { mutableStateOf("") }
    var smallSize by rememberSaveable{ mutableStateOf(false) }
    val animatedSizeDp by animateDpAsState(targetValue = if (smallSize) 100.dp else 200.dp)
    val boxSize by animateDpAsState(targetValue = if (smallSize) 150.dp else 250.dp)

    Box {
        Column {
            LazyRow(modifier = Modifier.padding(5.dp)) {
                item {
                    CategoryButton(
                        buttonState = CategoryButtonState.Category,
                        onClick = {
                            expanded =
                                !(expanded && categoryRoute == CategoryButtonState.Category.route)
                            categoryRoute = CategoryButtonState.Category.route
                        })
                    Spacer(modifier = Modifier.width(5.dp))
                    CategoryButton(
                        buttonState = CategoryButtonState.Brand,
                        onClick = {
                            expanded =
                                !(expanded && categoryRoute == CategoryButtonState.Brand.route)
                            categoryRoute = CategoryButtonState.Brand.route
                        }
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CategoryButton(
                        buttonState = CategoryButtonState.Color,
                        onClick = {
                            expanded =
                                !(expanded && categoryRoute == CategoryButtonState.Color.route)
                            categoryRoute = CategoryButtonState.Color.route
                        }
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CategoryButton(
                        buttonState = CategoryButtonState.Manufacturer,
                        onClick = {
                            expanded =
                                !(expanded && categoryRoute == CategoryButtonState.Manufacturer.route)
                            categoryRoute = CategoryButtonState.Manufacturer.route
                        }
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    CategoryButton(
                        buttonState = CategoryButtonState.Material,
                        onClick = {
                            expanded =
                                !(expanded && categoryRoute == CategoryButtonState.Material.route)
                            categoryRoute = CategoryButtonState.Material.route
                        }
                    )
                }
            }
            AnimatedVisibility(expanded) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(boxSize),
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(animatedSizeDp)
                        ) {
                            when (categoryRoute) {
                                CategoryButtonState.Category.route ->  {
                                    Selector(searchViewModel.searchBarState.categories)
                                    smallSize = true
                                }
                                CategoryButtonState.Brand.route -> {
                                    Selector(searchViewModel.searchBarState.brands)
                                    smallSize = false
                                }
                                CategoryButtonState.Color.route -> {
                                    Selector(searchViewModel.searchBarState.colors)
                                    smallSize = false
                                }
                                CategoryButtonState.Manufacturer.route -> {
                                    Selector(searchViewModel.searchBarState.manufacturer)
                                    smallSize = false
                                }
                                CategoryButtonState.Material.route -> {
                                    Selector(searchViewModel.searchBarState.material)
                                    smallSize = false
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 10.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Button(onClick = {
                                searchViewModel.filter()
                            }) {
                                Text(text = "Применить фильтр")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryButton(
    buttonState: CategoryButtonState,
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .size(buttonState.width, buttonState.height),
        shape = CircleShape,
    ) {
        Text(text = buttonState.name)
    }
}
