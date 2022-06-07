package com.example.shopapp.presentation.screen.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class LoginState (
    var email: MutableState<String>             = mutableStateOf(""),
    var password: MutableState<String>          = mutableStateOf(""),
    var confirmPassword: MutableState<String>   = mutableStateOf(""),
    var isRememberState: MutableState<Boolean>  =  mutableStateOf(false),
    var isDarkTheme: MutableState<Boolean>      =  mutableStateOf(false),
)
