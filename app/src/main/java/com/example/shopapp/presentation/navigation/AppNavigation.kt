package com.example.shopapp.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shopapp.presentation.screen.DetailedScreen
import com.example.shopapp.presentation.screen.MainPage
import com.google.accompanist.pager.ExperimentalPagerApi


@Composable
@ExperimentalPagerApi
fun AppNavigation() {
    val bottomItems by remember {
        mutableStateOf(listOf ("Home", "Search", "Cart", "History", "Profile"))
       }
    Surface{
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    bottomItems.forEach { screen ->
                        BottomNavigationItem(selected = false,
                            onClick = { navController.navigate(screen) },
                            label = { Text(screen) }, icon = { })
                    }// Foreach
                } //BottomNavigation
            } // bottomBar
        ) { innerPadding ->
            NavHost(navController = navController, startDestination = "Home") {
                composable("Home") {
                    Box(modifier = androidx.compose.ui.Modifier.padding(innerPadding))
                    {
                        DetailedScreen()
                    }
                }
            }
        }
    }
}