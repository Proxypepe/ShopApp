package com.example.shopapp.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shopapp.repository.TypeConvertor
import com.example.shopapp.repository.local.FavoriteLocalRepository
import com.example.shopapp.repository.local.entity.FavoriteEntity
import com.example.shopapp.repository.local.entity.ProductEntity
import com.example.shopapp.repository.remote.models.ProductDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class FavoriteViewModel(private val favoriteLocalRepository: FavoriteLocalRepository) :
    ViewModel() {

    private var _favorites: MutableStateFlow<List<FavoriteEntity>> = MutableStateFlow(emptyList())
    var favorites = _favorites.asStateFlow()


    fun getFavorites() = viewModelScope.launch {
        favoriteLocalRepository.getFavorites().collect {
            _favorites.value = it
        }
    }

    fun divideFavorites(): Pair<MutableList<FavoriteEntity?>, MutableList<FavoriteEntity?>> {
        val maxSize = _favorites.value.size
        val middle = maxSize / 2
        val first: MutableList<FavoriteEntity?> =
            _favorites.value.subList(0, middle).toMutableList()
        val second: MutableList<FavoriteEntity?> =
            _favorites.value.subList(middle, maxSize).toMutableList()
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

    fun onFavoritesChange(product: ProductEntity) {
        if (!contains(product))
            addFavorite(product)
        else
            deleteFavorite(product)
    }

    private fun addFavorite(product: ProductEntity) {
        val favoriteEntity = FavoriteEntity(product_id = product.prod_id, product = product)
        _insertFavorite(favoriteEntity)
    }

    private fun addFavorite(product: ProductDto) {
        val favoriteEntity = TypeConvertor.toFavoriteEntityFromProductDto(product)
        _insertFavorite(favoriteEntity)
    }

    private fun deleteFavorite(product: ProductDto) {
        val favoriteEntity = TypeConvertor.toFavoriteEntityFromProductDto(product)
        _deleteFavorite(favoriteEntity)
    }

    private fun deleteFavorite(product: ProductEntity) {
        val favoriteEntity = FavoriteEntity(product_id = product.prod_id, product = product)
        _deleteFavorite(favoriteEntity)
    }

    fun contains(product: ProductDto): Boolean {
        val entity = TypeConvertor.toFavoriteEntityFromProductDto(product)
        return favorites.value.contains(entity)
    }

    fun contains(product: ProductEntity): Boolean {
        val entity = TypeConvertor.toFavoriteEntityFromProductEntity(product)
        return favorites.value.contains(entity)
    }

    private fun _insertFavorite(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        favoriteLocalRepository.insertFavorite(favoriteEntity)
    }

    private fun _deleteFavorite(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        favoriteLocalRepository.deleteFavorite(favoriteEntity)
    }
}


class FavoriteViewModelFactory(private val favoriteLocalRepository: FavoriteLocalRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteViewModel(favoriteLocalRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
