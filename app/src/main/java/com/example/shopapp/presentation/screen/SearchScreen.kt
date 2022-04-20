package com.example.shopapp.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shopapp.domain.SearchViewModel


@ExperimentalComposeUiApi
@Composable
fun SearchScreen(searchViewModel: SearchViewModel, navController: NavController) {

    val query = searchViewModel.query.value
    val allProducts = searchViewModel.filtered.collectAsState(initial = emptyList()).value
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colors.primary,
            elevation = 8.dp,
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
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
                        fontSize = 15.sp),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            searchViewModel.newSearch(query)
                            keyboardController?.hide()
                            focusManager.clearFocus()}),
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
    var category by remember { mutableStateOf(0)}

    Box {
        Column {
            LazyRow {
                item {
                    Spacer(modifier = Modifier.width(5.dp))
                    OutlinedButton(
                        onClick = {
                            expanded = !(expanded && category == 1)
                            category = 1
                        },
                        modifier = Modifier
                            .size(120.dp, 35.dp),
                        shape = CircleShape,
                    ) {
                        Text(text = "Категория")
                    }
                    OutlinedButton(
                        onClick = {
                            expanded = !(expanded && category == 2)
                            category = 2
                        },
                        modifier = Modifier
                            .size(100.dp, 35.dp),
                        shape = CircleShape,
                    ) {
                        Text(text = "Бренд")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    OutlinedButton(
                        onClick = {
                            expanded = !(expanded && category == 3)
                            category = 3
                        },
                        modifier = Modifier
                            .size(100.dp, 35.dp),
                        shape = CircleShape,
                    ) {
                        Text(text = "Цвет")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    OutlinedButton(
                        onClick = {
                            expanded = !(expanded && category == 4)
                            category = 4
                        },
                        modifier = Modifier
                            .size(150.dp, 35.dp),
                        shape = CircleShape,
                    ) {
                        Text(text = "Производитель")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    OutlinedButton(
                        onClick = {
                            expanded = !(expanded && category == 5)
                            category = 5
                        },
                        modifier = Modifier
                            .size(120.dp, 35.dp),
                        shape = CircleShape,
                    ) {
                        Text(text = "Материал")
                    }
                }
            }
            AnimatedVisibility(expanded) {
                Box(
                    modifier = Modifier.fillMaxWidth().height(150.dp),
                ){
                    when(category) {
                        1 -> CategorySelector()
                        2 -> BrandSelector()
                        3 -> ColorSelector()
                        4 -> ManufacturingSelector()
                        5 -> MaterialSelector()
                    }
                }
            }
        }
    }
}


@Composable
fun CategorySelector() {
    val acoustic = remember { mutableStateOf(false) }
    val bass = remember { mutableStateOf(false) }
    val classic = remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = acoustic.value,
                onCheckedChange = { acoustic.value = it }
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Акустическая гитара")
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = bass.value,
                onCheckedChange = { bass.value = it }
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Классическая гитара")
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = classic.value,
                onCheckedChange = { classic.value = it }
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Басс гитара")
        }
    }
}

@Composable
fun BrandSelector() {
    Text(text = "Hello")
}

@Composable
fun ColorSelector() {

}

@Composable
fun ManufacturingSelector() {

}

@Composable
fun MaterialSelector() {

}
