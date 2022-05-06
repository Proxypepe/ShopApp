package com.example.shopapp.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shopapp.domain.common.EventHandler
import com.example.shopapp.domain.common.LoginEvent
import com.example.shopapp.presentation.screen.login.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class LoginViewModel: EventHandler<LoginEvent>, ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val signInState = _loginState.asStateFlow()


    fun onChangePassword(newPassword: String) {
        _loginState.value.password.value = newPassword
    }

    fun onChangeEmail(newEmail: String) {
        _loginState.value.email.value = newEmail
    }

    fun onChangeConfirmPassword(newPassword: String) {
        _loginState.value.confirmPassword.value = newPassword
    }

    private fun comparePasses(): Boolean =
        _loginState.value.password.value == _loginState.value.confirmPassword.value

    override fun obtainEvent(event: LoginEvent) {
        TODO("Not yet implemented")
    }
}

class LoginViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
