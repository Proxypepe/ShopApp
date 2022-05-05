package com.example.shopapp.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shopapp.divideList
import com.example.shopapp.domain.SearchViewModel
import com.example.shopapp.domain.filters.CategoryButtonState
import com.example.shopapp.domain.filters.FilterCategory
import com.example.shopapp.presentation.screen.components.SearchCard


@ExperimentalComposeUiApi
@Composable
fun SearchScreen(searchViewModel: SearchViewModel, navController: NavHostController) {

    val query = searchViewModel.query.value
    val allProducts = searchViewModel.filtered.collectAsState(initial = emptyList()).value
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.background(MaterialTheme.colors.background)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primary)
                    .clip(RoundedCornerShape(5.dp)),

                ) {
                    TextField(
                        shape = RoundedCornerShape(10.dp),
                        value = query,
                        onValueChange = {
                            searchViewModel.onQueryChanged(it)
                        },
                        label = {
                            Text(text = "Search")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(MaterialTheme.colors.surface),
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = "Search"
                            )
                        },
                        textStyle = TextStyle(
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 15.sp
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                searchViewModel.newSearch(query)
                                keyboardController?.hide()
                                focusManager.clearFocus()
                            }),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done,
                        ),

                    )
            }
        }

        Spacer(modifier = Modifier.height(5.dp))
        FilterButtons(searchViewModel)
        LazyColumn {
            items(allProducts) { product ->
                SearchCard(product = product, navController = navController)
            }
        }
    }
}


@Composable
fun FilterButtons(searchViewModel: SearchViewModel) {
    var expanded by remember { mutableStateOf(false) }
    var categoryRoute by remember { mutableStateOf("")}
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


@Composable
fun Selector(categories: List<FilterCategory>) {
    LazyColumn(modifier = Modifier
        .fillMaxWidth()) {
        item {
            val pair = divideList(categories)
            val first = pair.first
            val second = pair.second
            for (i in 0 until first.size)
                SelectorBlock(first[i], second[i])
        }
    }
}

@Composable
fun SelectorBlock(leftCategory: FilterCategory?, rightCategory: FilterCategory?) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxWidth(0.5f)) {
            if (leftCategory != null) {
                SelectorCard(leftCategory)
            }
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            if (rightCategory != null) {
                SelectorCard(rightCategory)
            }
        }
    }
}

@Composable
fun SelectorCard(category: FilterCategory) {
    Box ( modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        CustomCheckBox(
            name = category.name,
            checked = category.selected,
            onCheckedChange = {
                category.selected.value = it },
            onTextClicked = {category.selected.value = !category.selected.value }
        )
    }
}


@Composable
fun CustomCheckBox(
    name: String,
    checked: MutableState<Boolean>,
    onCheckedChange: ((Boolean) -> Unit),
    onTextClicked: (() -> Unit)
) {
    // FIXME: RENAME FUNCTION
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { onCheckedChange(it) }
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = name,
            modifier = Modifier.clickable {
                onTextClicked()
            }
        )
    }
}
