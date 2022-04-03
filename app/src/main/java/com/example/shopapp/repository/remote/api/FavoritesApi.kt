package com.example.shopapp.repository.remote.api

import com.example.shopapp.repository.remote.models.FavoriteDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoritesApi {

    @POST("/add")
    suspend fun addFavorite(@Body favorite: FavoriteDto): Response<FavoriteDto>

    @DELETE("/delete/{id}")
    suspend fun deleteFavorite(@Path("id")id : Int)
}