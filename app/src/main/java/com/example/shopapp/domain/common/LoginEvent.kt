package com.example.shopapp.domain.common

sealed class LoginEvent {

    object Registration: LoginEvent()
    object Authentication: LoginEvent()

    data class EmailChanged(val value: String): LoginEvent()
    data class PasswordChanged(val value: String): LoginEvent()
    data class ConfirmPasswordChanged(val value: String): LoginEvent()
}