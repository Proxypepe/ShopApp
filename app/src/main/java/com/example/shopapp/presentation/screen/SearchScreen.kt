package com.example.shopapp.presentation.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavHostController
import com.example.shopapp.domain.SearchViewModel
import com.example.shopapp.domain.filters.SearchBarState
import com.example.shopapp.presentation.screen.components.SearchCard


@ExperimentalComposeUiApi
@Composable
fun SearchScreen(searchViewModel: SearchViewModel, navController: NavHostController) {

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
                    Spacer(modifier = Modifier.width(5.dp))
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
                var height = 0.dp
                when(category) {
                    1 -> height = 200.dp
                    2 -> height = 200.dp
                    3 -> height = 200.dp
                    4 -> height = 200.dp
                    5 -> height = 200.dp
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height),
                ){
                    when(category) {
                        1 -> CategorySelector(searchViewModel)
                        2 -> BrandSelector(searchViewModel)
                        3 -> ColorSelector(searchViewModel)
                        4 -> ManufacturerSelector(searchViewModel)
                        5 -> MaterialSelector()
                    }
                }
            }
        }
    }
}


@Composable
fun CategorySelector(searchViewModel: SearchViewModel) {
    val categories = listOf(
        searchViewModel.searchBarState.acoustic,
        searchViewModel.searchBarState.classic,
        searchViewModel.searchBarState.bass,
        searchViewModel.searchBarState.electro
    )
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        categories.forEach { category ->
            CustomCheckBox(
                name = category.category,
                checked = category.selected,
                onCheckedChange = {
                    Log.d("Acoustic section", "$it and ${category.selected}")
                    category.selected.value = it },
                onTextClicked = {category.selected.value = !category.selected.value }
            )
        }
    }
}

@Composable
fun BrandSelector(searchViewModel: SearchViewModel) {
    val brands = listOf(
        searchViewModel.searchBarState.prima,
        searchViewModel.searchBarState.yamaha,
        searchViewModel.searchBarState.crafter,
        searchViewModel.searchBarState.enyaGuitars,
        searchViewModel.searchBarState.framus,
        searchViewModel.searchBarState.ovation,
        searchViewModel.searchBarState.cort,
        searchViewModel.searchBarState.fender,
        searchViewModel.searchBarState.lAG,
        searchViewModel.searchBarState.ortega,
        searchViewModel.searchBarState.kremona,
        searchViewModel.searchBarState.GEWA,
        searchViewModel.searchBarState.django,
        searchViewModel.searchBarState.alhambra,
        searchViewModel.searchBarState.tokai,
        searchViewModel.searchBarState.vintage,
        searchViewModel.searchBarState.inspector,
        searchViewModel.searchBarState.DBZ,
        searchViewModel.searchBarState.squier,
        searchViewModel.searchBarState.ibanez,
        searchViewModel.searchBarState.FGN,
        searchViewModel.searchBarState.PRS,
        searchViewModel.searchBarState.mayones,
        searchViewModel.searchBarState.angelico,
        searchViewModel.searchBarState.fernandes,

    )
    LazyColumn(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        items(brands) { brand ->
            CustomCheckBox(
                name = brand.brand,
                checked = brand.selected,
                onCheckedChange = {
                    Log.d("Acoustic section", "$it and ${brand.selected}")
                    brand.selected.value = it },
                onTextClicked = {brand.selected.value = !brand.selected.value }
            )
        }
    }
}

@Composable
fun ColorSelector(searchViewModel: SearchViewModel) {
    val colors = listOf(
        searchViewModel.searchBarState.natural,
        searchViewModel.searchBarState.black,
        searchViewModel.searchBarState.burst,
        searchViewModel.searchBarState.brown,
        searchViewModel.searchBarState.white,
        searchViewModel.searchBarState.silver,
        searchViewModel.searchBarState.red,
        searchViewModel.searchBarState.blackE,
        searchViewModel.searchBarState.grey,
        searchViewModel.searchBarState.blue,
        searchViewModel.searchBarState.darkBlue,
        searchViewModel.searchBarState.orange,
        searchViewModel.searchBarState.pink,
        searchViewModel.searchBarState.nacre,
        searchViewModel.searchBarState.yellow,
        searchViewModel.searchBarState.green,
    )
    LazyColumn(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        items(colors) { color ->
            CustomCheckBox(
                name = color.color,
                checked = color.selected,
                onCheckedChange = {
                    Log.d("Acoustic section", "$it and ${color.selected}")
                    color.selected.value = it },
                onTextClicked = {color.selected.value = !color.selected.value }
            )
        }
    }
}

@Composable
fun ManufacturerSelector(searchViewModel: SearchViewModel) {
    val manufacturers = listOf(
        searchViewModel.searchBarState.china,
        searchViewModel.searchBarState.indonesia,
        searchViewModel.searchBarState.bulgaria,
        searchViewModel.searchBarState.romania,
        searchViewModel.searchBarState.spain,
        searchViewModel.searchBarState.russia,
        searchViewModel.searchBarState.korea,
        searchViewModel.searchBarState.japan,
        searchViewModel.searchBarState.mexico,
        searchViewModel.searchBarState.poland,
        searchViewModel.searchBarState.USA,
    )
    LazyColumn(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        items(manufacturers) { manufacturer ->
            CustomCheckBox(
                name = manufacturer.country,
                checked = manufacturer.selected,
                onCheckedChange = {
                    Log.d("Acoustic section", "$it and ${manufacturer.selected}")
                    manufacturer.selected.value = it },
                onTextClicked = {manufacturer.selected.value = !manufacturer.selected.value }
            )
        }
    }
}

@Composable
fun MaterialSelector() {

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