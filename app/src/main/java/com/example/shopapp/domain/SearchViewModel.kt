package com.example.shopapp.domain

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shopapp.domain.filters.FilterCategory
import com.example.shopapp.domain.filters.SearchBarState
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
    val searchBarState = SearchBarState()

    init {
        initProducts()
    }

    fun newSearch(query: String) {
        if (query.isEmpty())
            filtered.value = allProducts
        else
            filtered.value = querySearch(query)
    }

    fun filter() {
        var products: Set<ProductDto> = emptySet()
        products = products union filterByAttribute("category")
        products = products union filterByAttribute("color")
        products = products union filterByAttribute("brand")
        products = products union filterByAttribute("produced")
        products = products union filterByAttribute("neck_material")
        if (products.isEmpty())
            filtered.value = allProducts
        else
            filtered.value = products.toList()
    }

    fun onQueryChanged(query: String){
        this.query.value = query
    }

    private fun querySearch(query: String) = allProducts.filter {
        it.name.contains(query)
    }

    private fun filterByAttribute(attribute: String): Set<ProductDto> {
        var tempSet: Set<ProductDto>
        var resultSet: Set<ProductDto> = emptySet()
        peekListState(attribute).forEach { filter_category ->
            if (filter_category.selected.value)
            {
                tempSet = allProducts.filter { product ->
                    product.peekAttribute(attribute) == filter_category.name
                }.toSet()
                resultSet = resultSet union tempSet
            }
        }
        return resultSet
    }

    private fun ProductDto.peekAttribute(name: String): String {
        return when(name) {
            "category" -> category
            "color" -> color ?: ""
            "brand" -> brand
            "produced" -> produced ?: ""
            "neck_material" -> neck_material ?: ""
            else -> ""
        }
    }

    private fun peekListState(name: String): List<FilterCategory> {
        return when(name) {
            "category" -> searchBarState.categories
            "color" -> searchBarState.colors
            "brand" -> searchBarState.brands
            "produced" -> searchBarState.manufacturer
            "neck_material" -> searchBarState.material
            else -> emptyList()
        }
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
