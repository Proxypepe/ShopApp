package com.example.shopapp.presentation.navigation

sealed class NavigationRouter(val route: String ) {
    object Home: NavigationRouter("home")
    object Search: NavigationRouter("search")
    object Cart: NavigationRouter("cart")
    object Favorite: NavigationRouter("favorite")
    object Profile: NavigationRouter("profile")
    object Detailed: NavigationRouter("detailed")
    object CommentRead: NavigationRouter("comment_read")
    object CommentWrite: NavigationRouter("comment_write")
    object SignUp: NavigationRouter("signup")
    object SignIn: NavigationRouter("signin")
}
