package com.example.shopapp.repository.local

import com.example.shopapp.repository.local.dao.FavoriteDao
import com.example.shopapp.repository.local.entity.FavoriteEntity


class FavoriteLocalRepository(private val favoriteDao: FavoriteDao) {

    fun getFavorites() = favoriteDao.getFavorites()

    suspend fun insertFavorite(favorite: FavoriteEntity) = favoriteDao.insertFavorite(favorite)

    suspend fun deleteFavorite(favorite: FavoriteEntity) = favoriteDao.deleteFavorite(favorite)
}
