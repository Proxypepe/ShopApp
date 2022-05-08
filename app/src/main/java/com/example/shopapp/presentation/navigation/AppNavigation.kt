package com.example.shopapp.presentation.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
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
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.shopapp.domain.*
import com.example.shopapp.presentation.screen.*
import com.example.shopapp.presentation.screen.login.SighInScreen
import com.example.shopapp.presentation.screen.login.SighUpScreen
import com.example.shopapp.repository.remote.models.ProductDto
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi


@Composable
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
fun AppNavigation(mainPageViewModel: MainViewModel, favoriteViewModel: FavoriteViewModel,
                  cartViewModel: CartViewModel, searchViewModel: SearchViewModel,
                  loginViewModel: LoginViewModel
) {
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    val badgeCount = cartViewModel.cart?.collectAsState(initial = listOf())?.value?.size

    Scaffold(
        bottomBar = {
            if (currentRoute == NavigationRouter.SignIn.route ||
                currentRoute == NavigationRouter.SignUp.route) {

            } else {
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
                        ),
                    ),
                    navController = navController,
                    onItemClick = {
                        navController.navigate(it.route)
                    }
                )
            }
        } // bottomBar
    ) { innerPadding ->
        AnimatedNavHost(navController = navController, startDestination = NavigationRouter.Home.route,
            popEnterTransition = { slideInHorizontally(
                initialOffsetX = {-300},
                animationSpec = tween(
                    durationMillis = 500,
                    easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = { slideOutHorizontally(
                targetOffsetX = { -300},
                animationSpec = tween(
                    durationMillis = 500,
                    easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            }) {
            composable(NavigationRouter.Home.route,
                )
            {
                Box(modifier = Modifier.padding(innerPadding))
                {
                    MainPage(mainPageViewModel, navController)
                }
            }
            composable(NavigationRouter.Search.route) {
                Box(modifier = Modifier.padding(innerPadding))
                {
                    SearchScreen(searchViewModel, navController)
                }
            }
            composable(NavigationRouter.Cart.route) {
                Box(modifier = Modifier.padding(innerPadding))
                {
                    CartScreen(cartViewModel, mainPageViewModel, favoriteViewModel, navController)
                }
            }
            composable(NavigationRouter.Favorite.route) {
                Box(modifier = Modifier.padding(innerPadding))
                {
                    FavoriteScreen(favoriteViewModel, navController)
                }
            }
            composable(NavigationRouter.Profile.route) {
                Box(modifier = Modifier.padding(innerPadding))
                {
                    Profile(navController)
                }
            }
            composable(NavigationRouter.SignIn.route) {
                Box(modifier = Modifier.padding(innerPadding))
                {
                    SighInScreen(loginViewModel, navController)
                }
            }

            composable(NavigationRouter.SignUp.route) {
                Box(modifier = Modifier.padding(innerPadding))
                {
                    SighUpScreen(loginViewModel, navController)
                }
            }

            composable(NavigationRouter.Detailed.route) {
                navController.previousBackStackEntry?.arguments?.
                getParcelable<ProductDto>("PRODUCT")?.let {
                    Box(modifier = Modifier.padding(innerPadding))
                    {
                        DetailedScreen(mainPageViewModel, favoriteViewModel,
                            cartViewModel, it, navController)
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
    navController: NavHostController,
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
                            BadgedBox(
                                badge = { Badge {
                                    Text(text = item.badgeCount.toString())
                                    }
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
