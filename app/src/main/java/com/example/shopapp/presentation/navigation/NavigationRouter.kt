package com.example.shopapp.presentation.navigation

sealed class NavigationRouter(val route: String ) {
    object Home: NavigationRouter("home")
    object Search: NavigationRouter("search")
    object Cart: NavigationRouter("cart")
    object Favorite: NavigationRouter("favorite")
    object Profile: NavigationRouter("profile")
    object Detailed: NavigationRouter("detailed")
}
