package com.example.shopapp.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shopapp.repository.TypeConvertor
import com.example.shopapp.repository.remote.models.ProductDto
import com.example.shopapp.repository.remote.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

class MainViewModel(private val productRepository: ProductRepository,
                    private val cartViewModel: CartViewModel): ViewModel() {
    var recommendedProducts: MutableStateFlow<List<ProductDto>> = MutableStateFlow(emptyList())
    var detailedInfo: MutableStateFlow<ProductDto> = MutableStateFlow(TypeConvertor.initialProductDto) // MutableStateFlow
    var loading: MutableStateFlow<Boolean> = MutableStateFlow(false)


    fun getRecommendedProduct() = viewModelScope.launch {
        loading.value = true
        try {
            val recommended = productRepository.getRangeProduct(1, 5)
            if (recommended.isSuccessful && recommended.body() != null ) {
                println(recommended)
                recommended.body()?.let { recommendedProducts.value = it }
                loading.value = false
            }
        } catch (error: SocketTimeoutException) {
            loading.value = false
        }
    }

    fun getDetailById(id: Int) = viewModelScope.launch {
        val product = productRepository.getDetailById(id)
        if (product.isSuccessful && product.body() != null ) {
            product.body()?.let { detailedInfo.value = it }
        }
    }

    fun addToCart(productDto: ProductDto) {
        cartViewModel.addToCart(productDto)
    }

    fun deleteProductById(id: Int) {
        cartViewModel.deleteProductById(id)
    }
}



class MainViewModelFactory(private val productRepository: ProductRepository,
                           private val cartViewModel: CartViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(productRepository, cartViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}