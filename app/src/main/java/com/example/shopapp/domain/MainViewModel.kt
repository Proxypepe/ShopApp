package com.example.shopapp.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shopapp.repository.local.ProductLocalRepository
import com.example.shopapp.repository.local.entity.ProductEntity
import com.example.shopapp.repository.remote.models.ProductDto
import com.example.shopapp.repository.remote.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel(private val productRepository: ProductRepository,
                    private val localRepository: ProductLocalRepository): ViewModel() {

    var recommendedProducts: Flow<List<ProductDto>>? = null
    var detailedInfo: Flow<ProductDto>? = null
    var loading: Flow<Boolean> = flow { emit(false) }
    var cart:  Flow<List<ProductEntity>>? = null

    fun getCart() = viewModelScope.launch {
        cart = localRepository.getProducts()
    }

    fun getRecommendedProduct() = viewModelScope.launch {
        loading = flow {
            emit(true)
        }
        val recommended = productRepository.getRangeProduct(1, 5)
        if (recommended.isSuccessful && recommended.body() != null ) {
            println(recommended)
            recommendedProducts = flow {
                recommended.body()?.let { emit(it) }
                loading = flow {
                    emit(false)
                }
            }
        }
    }

    fun getDetailById(id: Int) = viewModelScope.launch {
        val product = productRepository.getDetailById(id)
        if (product.isSuccessful && product.body() != null ) {
            detailedInfo = flow {
                product.body()?.let { emit(it) }
            }
        }
    }

    fun addToCart(productDto: ProductDto) {
        _insert(ProductEntity(
            prod_id = productDto.prod_id,
            name = productDto.name,
            description = productDto.description ?: "",
            price = productDto.price,
            stock = productDto.stock,
            category = productDto.category,
            brand = productDto.brand,
            shell_type = productDto.shell_type,
            top_deck = productDto.top_deck,
            top_material = productDto.top_material,
            back_deck = productDto.back_deck,
            neck_material = productDto.neck_material,
            overlay = productDto.overlay,
            strings = productDto.strings,
            neck_attachment = productDto.neck_attachment,
            mensura = productDto.mensura,
            neck_width = productDto.neck_width,
            color = productDto.color,
            tailpiece = productDto.tailpiece,
            produced = productDto.produced,
            cutout = productDto.cutout,
            varnish = productDto.varnish,
            form = productDto.form,
            specials = productDto.specials,
            lads = productDto.lads,
            link = productDto.link,
        ))
    }

    fun deleteProductById(id: Int) {
        _deleteProductById(id)
    }

    private fun _insert(productEntity: ProductEntity) = viewModelScope.launch {
        localRepository.insertProduct(productEntity)
    }

    private fun _deleteProductById(id: Int) = viewModelScope.launch {
        localRepository.deleteProductById(id)
    }
}



class MainViewModelFactory(private val productRepository: ProductRepository,
                           private val localRep: ProductLocalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(productRepository, localRep) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}