package com.example.shopapp.domain.common

import com.example.shopapp.repository.remote.models.UserDto
import kotlinx.coroutines.flow.StateFlow

sealed class LoginEvent {

    object Registration: LoginEvent()
    object Authentication: LoginEvent()

    data class EmailChanged(val value: String): LoginEvent()
    data class PasswordChanged(val value: String): LoginEvent()
    data class ConfirmPasswordChanged(val value: String): LoginEvent()

    data class RegisterUserUpdater(val updater: (StateFlow<UserDto>) -> Unit): LoginEvent()
}
