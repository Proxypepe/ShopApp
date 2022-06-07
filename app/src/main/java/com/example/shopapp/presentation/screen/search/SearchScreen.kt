package com.example.shopapp.presentation.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shopapp.domain.SearchViewModel
import com.example.shopapp.presentation.screen.search.components.FilterButtons
import com.example.shopapp.presentation.screen.search.components.SearchCard
import com.example.shopapp.ui.theme.AppTheme


@ExperimentalComposeUiApi
@Composable
fun SearchScreen(searchViewModel: SearchViewModel, navController: NavHostController) {

    val query = searchViewModel.query.value
    val allProducts = searchViewModel.filtered.collectAsState(initial = emptyList()).value
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.background(AppTheme.colors.background)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.primary),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppTheme.colors.primary)
                    .clip(RoundedCornerShape(5.dp)),

                ) {
                TextField(
                    shape = RoundedCornerShape(10.dp),
                    value = query,
                    onValueChange = {
                        searchViewModel.onQueryChanged(it)
                    },
                    placeholder = {
                        Text(text = "Search", color = AppTheme.textColors.primaryTextColor)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.Gray),
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = AppTheme.extendedColors.iconColor
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }) {
                            Icon(
                                Icons.Filled.Close,
                                contentDescription = "Close",
                                tint = AppTheme.extendedColors.iconColor
                            )
                        }
                    },
                    textStyle = TextStyle(
                        color = AppTheme.textColors.primaryTextColor,
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

