package com.example.shopapp.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shopapp.domain.common.EventHandler
import com.example.shopapp.domain.common.LoginEvent
import com.example.shopapp.presentation.screen.login.LoginState
import com.example.shopapp.repository.remote.models.UserBody
import com.example.shopapp.repository.remote.repository.AuthRepository
import com.example.shopapp.repository.remote.repository.RegisterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException


class LoginViewModel(private val registerRepository: RegisterRepository,
                     private val authRepository: AuthRepository
): EventHandler<LoginEvent>, ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val signInState = _loginState.asStateFlow()

    override fun obtainEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.Authentication -> authorize()
            is LoginEvent.ConfirmPasswordChanged -> onChangeConfirmPassword(event.value)
            is LoginEvent.EmailChanged -> onChangeEmail(event.value)
            is LoginEvent.PasswordChanged -> onChangePassword(event.value)
            LoginEvent.Registration -> register()
        }
    }

    private fun onChangePassword(newPassword: String) {
        _loginState.value.password.value = newPassword
    }

    private fun onChangeEmail(newEmail: String) {
        _loginState.value.email.value = newEmail
    }

    private fun onChangeConfirmPassword(newPassword: String) {
        _loginState.value.confirmPassword.value = newPassword
    }

    private fun register() = viewModelScope.launch {
        if (isEqualPasswords() && isNotEmptyFields()){
            try {
                val user =  registerRepository.createUser(
                    UserBody(
                        email = _loginState.value.email.value,
                        password = _loginState.value.password.value
                    )
                )

                if (user.body() != null && user.isSuccessful)
                {
                    Log.d("body", "${user.body()}")
                    clearFields()
                }
            } catch (error: SocketTimeoutException) {

            }

        }
    }


    private fun authorize() = viewModelScope.launch {
        if (isNotEmptyFields()) {
            try {
                val response = authRepository.checkAuth(
                    UserBody(
                        email = _loginState.value.email.value,
                        password = _loginState.value.password.value
                    )
                )
                if (response.body() != null && response.isSuccessful) {
                    Log.d("body", "${response.body()}")
                    clearFields()
                } else {
                    Log.d("body", "Something went wrong")
                }
            } catch (error: SocketTimeoutException) {


            }
        }
    }

    private fun isEqualPasswords(): Boolean =
        _loginState.value.password.value == _loginState.value.confirmPassword.value

    private fun isNotEmptyFields(): Boolean =
        _loginState.value.password.value.isNotEmpty() && _loginState.value.email.value.isNotEmpty()

    private fun clearFields() {
        _loginState.value.email.value = ""
        _loginState.value.password.value = ""
        _loginState.value.confirmPassword.value = ""
    }
}

class LoginViewModelFactory(private val registerRepository: RegisterRepository,
                            private val authRepository: AuthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(registerRepository, authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
