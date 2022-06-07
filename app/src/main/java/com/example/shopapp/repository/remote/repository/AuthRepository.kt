package com.example.shopapp.repository.remote.repository

import com.example.shopapp.repository.remote.api.AuthApi
import com.example.shopapp.repository.remote.models.UserBody
import com.example.shopapp.repository.remote.models.UserDto
import retrofit2.Response

class AuthRepository(private val authApi: AuthApi){
    suspend fun checkAuth(user: UserBody): Response<UserDto> = authApi.checkAuth(user)
}
