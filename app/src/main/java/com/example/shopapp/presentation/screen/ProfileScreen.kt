package com.example.shopapp.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shopapp.presentation.navigation.NavigationRouter

@Composable
fun Profile(navController: NavHostController) {
    Column {
        LogInSection(navController)
        Spacer(modifier = Modifier.height(70.dp))
        StaticMenu()
    }
}


@Composable
fun LogInSection(navController: NavHostController) {
    Box(modifier = Modifier
        .padding(start = 25.dp, top = 7.dp, end = 25.dp)
        .fillMaxWidth()
    ) {
        Column {
            Text(text = "Войдите или зарегистрируйтесь")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Чтобы получить ...")
            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = {
                navController.navigate(NavigationRouter.SignIn.route)
            },
                modifier = Modifier.fillMaxWidth()){
                Text(text = "Войти или зарегистрироваться")
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun StaticMenu() {
    Box(modifier = Modifier
        .padding(start = 25.dp)
        .fillMaxWidth()
    ) {
        Column {
            Text(text = "Приложение")
            Divider(color = Color.Gray, thickness = 1.dp)
            Text(text = "Цвет приложения")
            Divider(color = Color.Gray, thickness = 1.dp)
            Text(text = "О приложении")
            Divider(color = Color.Gray, thickness = 1.dp)
        }
    }
}
