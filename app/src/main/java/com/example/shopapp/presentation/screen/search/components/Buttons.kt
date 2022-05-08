package com.example.shopapp.presentation.screen.search.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopapp.domain.SearchViewModel
import com.example.shopapp.domain.filters.CategoryButtonState

@Composable
fun FilterButtons(searchViewModel: SearchViewModel) {
    var expanded by remember { mutableStateOf(false) }
    var categoryRoute by remember { mutableStateOf("") }
    Box {
        Column {
            LazyRow(modifier = Modifier.padding(5.dp))  {
                item {
                    CategoryButton(
                        buttonState = CategoryButtonState.Category,
                        onClick = {
                            expanded = !(expanded && categoryRoute == CategoryButtonState.Category.route)
                            categoryRoute = CategoryButtonState.Category.route
                        })
                    CategoryButton(
                        buttonState = CategoryButtonState.Brand,
                        onClick = {
                            expanded = !(expanded && categoryRoute == CategoryButtonState.Brand.route)
                            categoryRoute = CategoryButtonState.Brand.route
                        })
                    CategoryButton(
                        buttonState = CategoryButtonState.Color,
                        onClick = {
                            expanded = !(expanded && categoryRoute == CategoryButtonState.Color.route)
                            categoryRoute = CategoryButtonState.Color.route
                        })
                    CategoryButton(
                        buttonState = CategoryButtonState.Manufacturer,
                        onClick = {
                            expanded = !(expanded && categoryRoute == CategoryButtonState.Manufacturer.route)
                            categoryRoute = CategoryButtonState.Manufacturer.route
                        })
                    CategoryButton(
                        buttonState = CategoryButtonState.Material,
                        onClick = {
                            expanded = !(expanded && categoryRoute == CategoryButtonState.Material.route)
                            categoryRoute = CategoryButtonState.Material.route
                        })
                }
            }
            AnimatedVisibility(expanded) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                ){
                    Column{
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)) {
                            when(categoryRoute) {
                                CategoryButtonState.Category.route -> Selector(searchViewModel.searchBarState.categories)
                                CategoryButtonState.Brand.route -> Selector(searchViewModel.searchBarState.brands)
                                CategoryButtonState.Color.route -> Selector(searchViewModel.searchBarState.colors)
                                CategoryButtonState.Manufacturer.route -> Selector(searchViewModel.searchBarState.manufacturer)
                                CategoryButtonState.Material.route -> Selector(searchViewModel.searchBarState.material)
                            }
                        }
                        Box(modifier = Modifier
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