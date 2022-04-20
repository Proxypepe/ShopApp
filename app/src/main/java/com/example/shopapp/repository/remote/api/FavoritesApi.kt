package com.example.shopapp.repository.remote.api

import com.example.shopapp.repository.remote.models.FavoriteDto
import retrofit2.Response
import retrofit2.http.*

interface FavoritesApi {

    @GET("/all")
    suspend fun getFavorites(): Response<List<FavoriteDto>>

    @POST("/add")
    suspend fun addFavorite(@Body favorite: FavoriteDto): Response<FavoriteDto>

    @DELETE("/delete/{id}")
    suspend fun deleteFavorite(@Path("id")id : Int)
}