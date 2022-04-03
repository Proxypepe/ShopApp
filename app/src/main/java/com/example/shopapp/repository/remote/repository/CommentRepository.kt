package com.example.shopapp.repository.remote.repository

import com.example.shopapp.repository.remote.api.CommentApi
import com.example.shopapp.repository.remote.models.CommentDto
import retrofit2.Response

class CommentRepository(private val commentAPi: CommentApi) {

    suspend fun createComment(commentDto: CommentDto): Response<CommentDto> = commentAPi.createComment(commentDto)
}