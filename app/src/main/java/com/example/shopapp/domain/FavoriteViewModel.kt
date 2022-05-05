package com.example.shopapp.domain

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shopapp.repository.TypeConvertor
import com.example.shopapp.repository.local.FavoriteLocalRepository
import com.example.shopapp.repository.local.entity.FavoriteEntity
import com.example.shopapp.repository.local.entity.ProductEntity
import com.example.shopapp.repository.remote.models.ProductDto
import com.example.shopapp.repository.remote.models.UserDto
import com.example.shopapp.repository.remote.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch


class FavoriteViewModel(private val favoriteRepository: FavoriteRepository,
                        private val favoriteLocalRepository: FavoriteLocalRepository): ViewModel() {

    private var _favorites: MutableStateFlow<List<FavoriteEntity>> = MutableStateFlow(emptyList())
    var favorites = _favorites.asStateFlow()
    var userDto: UserDto? = null

    fun getFavorites() = viewModelScope.launch {
       favoriteLocalRepository.getFavorites().collect {
           _favorites.value = it
       }
//        if (userDto != null) {
//            val favoritesResponse = favoriteRepository.getFavorites()
//            if (favoritesResponse.isSuccessful && favoritesResponse.body() != null ) {
//                println(favoritesResponse)
//                favoritesResponse.body()?.let {
//                    favorites = flow {
//                        emit(it)
//                    }
//                }
//            }
//        } else {
//            favorites = favoriteLocalRepository.getFavorites()
//        }
    }

    fun divideFavorites(): Pair<MutableList<FavoriteEntity?>, MutableList<FavoriteEntity?>> {
        val maxSize = _favorites.value.size
        val middle = maxSize / 2
        val first: MutableList<FavoriteEntity?> = _favorites.value.subList(0, middle).toMutableList()
        val second: MutableList<FavoriteEntity?> = _favorites.value.subList(middle, maxSize).toMutableList()
        if (first.size > second.size)
            second.add(null)
        else if (first.size < second.size)
            first.add(null)

        return Pair(
            first,
            second
        )
    }

    fun onFavoritesChange(product: ProductDto) {
        if (!contains(product))
            addFavorite(product)
        else
            deleteFavorite(product)
    }

    fun addFavorite(product: ProductEntity) {
        val favoriteEntity = FavoriteEntity(product_id = product.prod_id, product = product)
        _insertFavorite(favoriteEntity)
    }

    fun addFavorite(product: ProductDto) {
        val favoriteEntity = TypeConvertor.toFavoriteEntityFromProductDto(product)
        _insertFavorite(favoriteEntity)
    }

    fun deleteFavorite(product: ProductDto) {
        val favoriteEntity = TypeConvertor.toFavoriteEntityFromProductDto(product)
        _deleteFavorite(favoriteEntity)
    }

    fun deleteFavorite(product: ProductEntity) {
        val favoriteEntity = FavoriteEntity(product_id = product.prod_id, product = product)
        _deleteFavorite(favoriteEntity)
    }

    fun contains(product: ProductDto): Boolean {
        val entity = TypeConvertor.toFavoriteEntityFromProductDto(product)
        return favorites.value.contains(entity)
    }

    private fun _insertFavorite(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        favoriteLocalRepository.insertFavorite(favoriteEntity)
    }

    private fun _deleteFavorite(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        favoriteLocalRepository.deleteFavorite(favoriteEntity)
    }
}


class FavoriteViewModelFactory(private val favoriteRepository: FavoriteRepository,
                               private val favoriteLocalRepository: FavoriteLocalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteViewModel(favoriteRepository, favoriteLocalRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
