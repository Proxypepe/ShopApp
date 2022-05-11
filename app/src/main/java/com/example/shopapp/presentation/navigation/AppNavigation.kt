package com.example.shopapp.presentation.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.shopapp.domain.*
import com.example.shopapp.presentation.navigation.components.BottomNavItem
import com.example.shopapp.presentation.navigation.components.BottomNavigationBar
import com.example.shopapp.presentation.screen.cart.CartScreen
import com.example.shopapp.presentation.screen.detailed.DetailedScreen
import com.example.shopapp.presentation.screen.detailed.components.CommentSubScreen
import com.example.shopapp.presentation.screen.detailed.components.MakeComment
import com.example.shopapp.presentation.screen.favorites.FavoriteScreen
import com.example.shopapp.presentation.screen.login.SighInScreen
import com.example.shopapp.presentation.screen.login.SighUpScreen
import com.example.shopapp.presentation.screen.main.MainPage
import com.example.shopapp.presentation.screen.profile.Profile
import com.example.shopapp.presentation.screen.search.SearchScreen
import com.example.shopapp.repository.remote.models.ProductDto
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi


@Composable
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
fun AppNavigation(mainPageViewModel: MainViewModel, favoriteViewModel: FavoriteViewModel,
                  cartViewModel: CartViewModel, searchViewModel: SearchViewModel,
                  loginViewModel: LoginViewModel, detailedViewModel: DetailedViewModel
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
            composable(NavigationRouter.Home.route) {
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
                    CartScreen(cartViewModel, mainPageViewModel, detailedViewModel,
                        favoriteViewModel, navController)
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
//                    mainPageViewModel.getDetailById(it.prod_id)
                    detailedViewModel.currentProduct = it
                    Box(modifier = Modifier.padding(innerPadding))
                    {
                        DetailedScreen(detailedViewModel, favoriteViewModel,
                            cartViewModel, it, navController
                        )
                    }
                }
            }

            composable(NavigationRouter.CommentRead.route) {
                // use navController arg
                detailedViewModel.currentProduct?.let {
                    CommentSubScreen(detailedViewModel, navController)
                }
            }

            composable(NavigationRouter.CommentWrite.route) {
                detailedViewModel.currentProduct?.let {
                    MakeComment(detailedViewModel, navController)
                }
            }
        }
    }
}


