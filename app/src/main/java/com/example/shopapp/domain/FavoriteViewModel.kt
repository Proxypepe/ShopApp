package com.example.shopapp.domain

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
import kotlinx.coroutines.launch


class FavoriteViewModel(private val favoriteRepository: FavoriteRepository,
                        private val favoriteLocalRepository: FavoriteLocalRepository): ViewModel() {

    var favorites: Flow<List<FavoriteEntity>>? = null
    var userDto: UserDto? = null

    fun getFavorites() = viewModelScope.launch {
        favorites = favoriteLocalRepository.getFavorites()
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

    fun addFavorite(product: ProductEntity) {
//        val favoriteEntity = TypeConvertor.toFavoriteEntityFromProductDto(productDto)
        val favoriteEntity = FavoriteEntity(product_id = product.prod_id, product = product)
        _insertFavorite(favoriteEntity)
    }

    fun deleteFavorite(productDto: ProductDto) {
        val favoriteEntity = TypeConvertor.toFavoriteEntityFromProductDto(productDto)
        _deleteFavorite(favoriteEntity)
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
