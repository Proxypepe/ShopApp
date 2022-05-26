package com.example.shopapp.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shopapp.repository.TypeConvertor
import com.example.shopapp.repository.local.ProductLocalRepository
import com.example.shopapp.repository.local.entity.ProductEntity
import com.example.shopapp.repository.remote.models.ProductDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val localRepository: ProductLocalRepository): ViewModel() {

    private var _cart:  MutableStateFlow<List<ProductEntity>> = MutableStateFlow(emptyList())
    var cart: StateFlow<List<ProductEntity>> = _cart.asStateFlow()

    fun initCart() = viewModelScope.launch {
        localRepository.getProducts().collect {
            _cart.value = it
        }
    }

    fun getCartSize() = _cart.value.size

    fun calculateFullPrice(): Long {
        var sum = 0L
        for (product in _cart.value)
            sum += convertPriceToInt(product.price)
        return sum
    }

    fun addToCart(productDto: ProductDto) {
        _insert(TypeConvertor.toProductEntityFromProductDto(productDto))
    }

    fun deleteProductById(id: Int) {
        _deleteProductById(id)
    }

    private fun convertPriceToInt(price: String) = price.removeSuffix("р.").replace(" ", "").toLong()

    private fun _insert(productEntity: ProductEntity) = viewModelScope.launch {
        localRepository.insertProduct(productEntity)
    }

    private fun _deleteProductById(id: Int) = viewModelScope.launch {
        localRepository.deleteProductById(id)
    }
}


class CartViewModelFactory(private val productLocalRepository: ProductLocalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CartViewModel(productLocalRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
