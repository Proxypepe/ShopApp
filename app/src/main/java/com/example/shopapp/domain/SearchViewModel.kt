package com.example.shopapp.domain

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shopapp.repository.remote.models.ProductDto
import com.example.shopapp.repository.remote.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

class SearchViewModel(
    private val productRepository: ProductRepository
): ViewModel() {
    private var allProducts: List<ProductDto> = emptyList()
    val filtered = MutableStateFlow(allProducts)
    var loading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val query = mutableStateOf("")

    init {
        initProducts()
    }

    fun newSearch(query: String) {
        if (query.isEmpty())
        {
            println(allProducts)
            filtered.value = allProducts
        } else {
            filtered.value = allProducts.filter {
                it.name.contains(query)
            }
        }
    }


    fun onQueryChanged(query: String){
        this.query.value = query
    }

    private fun initProducts() = viewModelScope.launch {
        loading.value = true
        try {
            val products = productRepository.getAllProducts()
            if (products.isSuccessful && products.body() != null ) {
                products.body()?.let {
                    allProducts = it
                    filtered.value = it
                }
                loading.value = false
            }
        } catch(error: SocketTimeoutException) {
            loading.value = false
        }
    }
}

class SearchViewModelFactory(private val productRepository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(productRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}