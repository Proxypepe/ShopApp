package com.example.shopapp.repository.remote.repository

import com.example.shopapp.repository.remote.api.RegisterApi
import com.example.shopapp.repository.remote.models.UserBody
import com.example.shopapp.repository.remote.models.UserDto
import retrofit2.Response

class RegisterRepository(private val registerApi: RegisterApi) {

        suspend fun createUser(user: UserBody): Response<UserDto> = registerApi.createUser(user)
}