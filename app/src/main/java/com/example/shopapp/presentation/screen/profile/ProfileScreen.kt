package com.example.shopapp.presentation.screen.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shopapp.domain.LoginViewModel
import com.example.shopapp.domain.common.LoginEvent
import com.example.shopapp.presentation.navigation.NavigationRouter
import com.example.shopapp.ui.theme.AppTheme

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Profile(
    loginViewModel: LoginViewModel,
    navController: NavHostController
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.primary)
                .height(60.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Профиль",
                style = AppTheme.typography.h1,
                color = Color.White
            )
        }

        if (loginViewModel.userData.value.email == "")
            LogInSection(navController)
        else
            ProfileSection(loginViewModel)

        Spacer(modifier = Modifier.height(70.dp))

        StaticMenu()
    }
}


@Composable
fun LogInSection(navController: NavHostController) {
    Box(
        modifier = Modifier
            .padding(start = 25.dp, top = 7.dp, end = 25.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(text = "Войдите или зарегистрируйтесь")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Чтобы получить ...")
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    navController.navigate(NavigationRouter.SignIn.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Войти или зарегистрироваться")
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun StaticMenu() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            Text(text = "Приложение", modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp)
                .clickable {

                }
            )
            Divider(color = Color.Gray, thickness = 1.dp)
            Text(text = "Цвет приложения", modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp)
                .clickable {

                }
            )
            Divider(color = Color.Gray, thickness = 1.dp)
            Text(text = "О приложении", modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp)
                .clickable {

                }
            )
            Divider(color = Color.Gray, thickness = 1.dp)
        }
    }
}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProfileSection(loginViewModel: LoginViewModel) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "Email:",
                style = AppTheme.typography.h3,
                color = AppTheme.textColors.primaryTextColor
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = loginViewModel.userData.value.email,
                style = AppTheme.typography.h3,
                color = AppTheme.textColors.primaryTextColor
            )
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                modifier = Modifier.width(100.dp),
                onClick = {
                    loginViewModel.obtainEvent(
                        LoginEvent.LogOut(context)
                    )
                }
            ) {
                Text(
                    text = "Выйти",
                    color = AppTheme.textColors.primaryButtonText
                )
            }
        }
    }
}
