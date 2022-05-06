package com.example.shopapp.presentation.screen.login

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shopapp.domain.LoginViewModel
import com.example.shopapp.presentation.navigation.NavigationRouter
import com.example.shopapp.presentation.screen.login.components.LoginTextField
import com.example.shopapp.ui.theme.FontSize

// alert dialog перенести ли данные из одного в другой
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SighUpScreen(loginViewModel: LoginViewModel, navController: NavHostController) {

    with (loginViewModel.signInState.value) {
        Box( contentAlignment = Alignment.Center, modifier = Modifier
            .padding(top = 100.dp, start = 50.dp,
                end = 50.dp, bottom = 50.dp )
        ){
            Column (modifier = Modifier
                .fillMaxSize(),
            ) {
                Text(
                    text = "Регистрация",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text ="Текст.",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.padding(25.dp))

                LoginTextField(
                    value = email.value,
                    onValueChange = {
                        loginViewModel.onChangeEmail(it)
                    },
                    label = "Почта",
                    placeholder = "Введите почту..."
                )

                Spacer(modifier = Modifier.padding(5.dp))

                LoginTextField(
                    value = password.value,
                    onValueChange = {
                        loginViewModel.onChangePassword(it)
                    },
                    label = "Пароль",
                    placeholder = "Введите пароль...",
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.padding(5.dp))

                LoginTextField(
                    value = confirmPassword.value,
                    onValueChange = {
                        loginViewModel.onChangeConfirmPassword(it)
                    },
                    label = "Почта",
                    placeholder = "Введите пароль повторно..."
                )

                Spacer(modifier = Modifier.padding(15.dp))

                Button( onClick = {

                },
                    modifier = Modifier.align(Alignment.End)
                ){
                    Text(text = "Зарегистрироваться")
                }

                Spacer(modifier = Modifier.fillMaxHeight(0.85f))

                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "У вас уже есть аккаунт?",
                        color = Color.Gray,
                        fontSize = FontSize.Middle.size
                    )
                    Spacer(modifier = Modifier.padding(end = 5.dp))
                    Text(
                        text = "Войти",
                        color = Color.Blue,
                        fontSize = FontSize.Middle.size,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(NavigationRouter.SignIn.route)
                            }
                    )
                }
            }
        }
    }
}
