package com.example.shopapp.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun FavoriteScreen(navController: NavController) {

    EmptyFavorites(navController)

}


@Composable
fun FavoriteCart() {


}

@Composable
fun EmptyFavorites(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text =  "В Избранном пока ничего нет",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text =  "Добавьте товар в избранное",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(7.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    navController.navigate("home")
                }) {
                    Text(text = "На главную")
                }
            }
        }
    }
}


@Preview
@Composable
fun FavoriteScreenPreview() {
    val navController = rememberNavController()
    FavoriteScreen(navController)
}

@Preview
@Composable
fun FavoriteCartPreview() {
    FavoriteCart()
}
