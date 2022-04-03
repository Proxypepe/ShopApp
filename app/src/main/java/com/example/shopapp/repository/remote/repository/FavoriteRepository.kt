package com.example.shopapp.repository.remote.repository

import com.example.shopapp.repository.remote.api.FavoritesApi
import com.example.shopapp.repository.remote.models.FavoriteDto
import retrofit2.Response

class FavoriteRepository(private val favoritesApi: FavoritesApi) {

    suspend fun addFavorite(favorite: FavoriteDto): Response<FavoriteDto>
        = favoritesApi.addFavorite(favorite)
    suspend fun deleteFavoriteById(id: Int) = favoritesApi.deleteFavorite(id)

}