package com.example.shopapp.repository.remote.api

import com.example.shopapp.repository.remote.models.CommentDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CommentApi {
    @POST("/add")
    suspend fun createComment(@Body commentDto: CommentDto): Response<CommentDto>
}