package com.example.shopapp.domain.common

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val isDark: Boolean = false,
    val isRememberPasswords: Boolean = false,
    val user_email: String = "",
    val user_password: String = "",
)
