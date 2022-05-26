package com.example.shopapp.domain.common

import android.content.Context
import com.example.shopapp.repository.remote.models.UserDto
import kotlinx.coroutines.flow.StateFlow

sealed class LoginEvent {

    data class Registration(val context: Context, val navigateUp: () -> Boolean): LoginEvent()
    data class Authentication(val context: Context, val navigateUp: () -> Boolean): LoginEvent()
    data class LogOut(val context: Context): LoginEvent()

    data class EmailChanged(val value: String): LoginEvent()
    data class PasswordChanged(val value: String): LoginEvent()
    data class ConfirmPasswordChanged(val value: String): LoginEvent()
}
