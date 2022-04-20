package com.example.shopapp.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shopapp.domain.CartViewModel
import com.example.shopapp.domain.FavoriteViewModel
import com.example.shopapp.domain.MainViewModel
import com.example.shopapp.domain.SearchViewModel
import com.example.shopapp.presentation.screen.*
import com.example.shopapp.repository.remote.models.ProductDto
import com.google.accompanist.pager.ExperimentalPagerApi


@Composable
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
fun AppNavigation(mainPageViewModel: MainViewModel, favoriteViewModel: FavoriteViewModel,
                  cartViewModel: CartViewModel, searchViewModel: SearchViewModel
) {
    val navController = rememberNavController()

    val badgeCount = cartViewModel.cart?.collectAsState(initial = listOf())?.value?.size

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = "home",
                        icon = Icons.Default.Home
                    ),
                    BottomNavItem(
                        name = "Search",
                        route = "search",
                        icon = Icons.Default.Search
                    ),
                    BottomNavItem(
                        name = "Cart",
                        route = "cart",
                        icon = Icons.Default.ShoppingCart,
                        badgeCount = badgeCount ?: 0
                    ),
                    BottomNavItem(
                        name = "Favorite",
                        route = "favorite",
                        icon = Icons.Default.Favorite
                    ),
                    BottomNavItem(
                        name = "Profile",
                        route = "profile",
                        icon = Icons.Default.Person
                    ),),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        } // bottomBar
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                Box(modifier = Modifier.padding(innerPadding))
                {
                    MainPage(mainPageViewModel, navController)
                }
            }
            composable("search") {
                Box(modifier = Modifier.padding(innerPadding))
                {
                    SearchScreen(searchViewModel, navController)
                }
            }
            composable("cart") {
                Box(modifier = Modifier.padding(innerPadding))
                {
                    CartScreen(cartViewModel, mainPageViewModel, favoriteViewModel, navController)
                }
            }
            composable("favorite") {
                Box(modifier = Modifier.padding(innerPadding))
                {
                    FavoriteScreen(favoriteViewModel, navController)
                }
            }
            composable("profile") {
                Box(modifier = Modifier.padding(innerPadding))
                {
                    Profile()
                }
            }

            composable("detailed") {
                navController.previousBackStackEntry?.arguments?.getParcelable<ProductDto>("PRODUCT")
                    ?.let {
                        Box(modifier = Modifier.padding(innerPadding))
                        {
                            DetailedScreen(mainPageViewModel = mainPageViewModel, product = it, navController = navController)
                        }
                    }
            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.White,
                onClick = { onItemClick(item) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (item.badgeCount > 0) {
                            BadgeBox(
                                badgeContent = {
                                    Text(text = item.badgeCount.toString())
                                }
                            ) {
                                Icon(imageVector = item.icon,
                                    contentDescription = item.name
                                )
                            }
                        } else {
                            Icon(imageVector = item.icon,
                                contentDescription = item.name
                            )
                        }
                        Text(text = item.name,
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp
                        )
                    }
                }
            )
        }
    }
}
