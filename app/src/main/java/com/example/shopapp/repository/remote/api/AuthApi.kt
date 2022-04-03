package com.example.shopapp.repository.remote.api

import com.example.shopapp.repository.remote.models.UserBody
import com.example.shopapp.repository.remote.models.UserResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthApi {
    @POST("./auth")
    suspend fun checkAuth(@Body user: UserBody): Response<UserResponseDto>
}
