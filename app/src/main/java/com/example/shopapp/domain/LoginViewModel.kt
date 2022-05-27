package com.example.shopapp.domain

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shopapp.domain.common.AppSettings
import com.example.shopapp.domain.common.EventHandler
import com.example.shopapp.domain.common.LoginEvent
import com.example.shopapp.presentation.screen.login.LoginState
import com.example.shopapp.repository.remote.models.UserBody
import com.example.shopapp.repository.remote.models.UserDto
import com.example.shopapp.repository.remote.repository.AuthRepository
import com.example.shopapp.repository.remote.repository.RegisterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException


class LoginViewModel(
    private val registerRepository: RegisterRepository,
    private val authRepository: AuthRepository,
    private val dataStore: DataStore<AppSettings>,
) : EventHandler<LoginEvent>, ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val signInState = _loginState.asStateFlow()

    private val _userData = MutableStateFlow(
        UserDto(
            email = "",
            password = "",
            role = ""
        )
    )
    val userData = _userData.asStateFlow()


    override fun obtainEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Authentication -> authorize(event.context, event.navigateUp)
            is LoginEvent.Registration -> register(event.context, event.navigateUp)
            is LoginEvent.ConfirmPasswordChanged -> onChangeConfirmPassword(event.value)
            is LoginEvent.EmailChanged -> onChangeEmail(event.value)
            is LoginEvent.PasswordChanged -> onChangePassword(event.value)
            is LoginEvent.LogOut -> logOut(event.context)
            is LoginEvent.SetTheme -> setAttrTheme(event.isDarkTheme)
            LoginEvent.ThemeChanged -> onChangeTheme()
            LoginEvent.InitUserData -> initUserData()
        }
    }


    private fun makeToast(context: Context, toastText: String) {
        Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()
    }

    private fun setAttrTheme(isDarkTheme: Boolean) {
        _loginState.value = _loginState.value.copy(isDarkTheme = mutableStateOf(isDarkTheme))
    }

    private fun onChangeTheme() = viewModelScope.launch {
        _loginState.value.isDarkTheme.value = !_loginState.value.isDarkTheme.value
        setTheme(_loginState.value.isDarkTheme.value)
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

    private fun logOut(context: Context) = viewModelScope.launch {
        _userData.value = UserDto(
            email = "",
            password = "",
            role = ""
        )
        forgotUserData()
        Toast.makeText(context, "Выход", Toast.LENGTH_LONG).show()
    }

    private fun register(context: Context, navigateUp: () -> Boolean) = viewModelScope.launch {
        if (isEqualPasswords() && isNotEmptyFields()) {
            try {
                val user = registerRepository.createUser(
                    UserBody(
                        email = _loginState.value.email.value,
                        password = _loginState.value.password.value
                    )
                )
                if (user.body() != null && user.isSuccessful) {
                    Log.d("body", "${user.body()}")
                    _userData.value = user.body()!!
                    clearFields()
                    makeToast(context, "Регистрация прошла успешно")
                    navigateUp()
                    navigateUp()
                    if (_loginState.value.isRememberState.value)
                        rememberUserData()
                } else {
                    makeToast(context, "Ошибка регистрации")
                }
            } catch (error: SocketTimeoutException) {
                makeToast(context, "Ошибка регистрации")
            }
        }
    }


    private fun authorize(context: Context, navigateUp: () -> Boolean) = viewModelScope.launch {
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
                    _userData.value = response.body()!!
                    clearFields()
                    makeToast(context, "Авторизация прошла успешно")
                    navigateUp()
                    if (_loginState.value.isRememberState.value)
                        rememberUserData()
                } else {
                    makeToast(context, "Ошибка авторизации")
                }
            } catch (error: SocketTimeoutException) {
                makeToast(context, "Ошибка авторизации")
            }
        }
    }

    private fun initUserData() = viewModelScope.launch {
        try {
            dataStore.data.collect {
                val response = authRepository.checkAuth(
                    UserBody(
                        email = it.user_email,
                        password = it.user_password
                    )
                )
                if (response.body() != null && response.isSuccessful) {
                    _userData.value = response.body()!!
                }
            }
        } catch (error: SocketTimeoutException) {

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

    private suspend fun setTheme(isDarkTheme: Boolean) {
        dataStore.updateData {
            it.copy(
                isDark = isDarkTheme
            )
        }
    }

    private suspend fun forgotUserData() {
        setUserData(
            user_email = "",
            user_password = "",
            isRememberPasswords = false,
        )
    }

    private suspend fun rememberUserData() {
        setUserData(
            user_email = userData.value.email,
            user_password = userData.value.password!!,
            isRememberPasswords = true,
        )
    }

    private suspend fun setUserData(
        user_email: String,
        user_password: String,
        isRememberPasswords: Boolean
    ) {
        dataStore.updateData {
            it.copy(
                user_email = user_email,
                user_password = user_password,
                isRememberPasswords = isRememberPasswords,
            )
        }
    }
}

class LoginViewModelFactory(
    private val registerRepository: RegisterRepository,
    private val authRepository: AuthRepository,
    private val dataStore: DataStore<AppSettings>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(registerRepository, authRepository, dataStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
