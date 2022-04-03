package com.example.shopapp.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shopapp.repository.remote.repository.ProductRepository
import kotlinx.coroutines.launch

class MainViewModel(private val productRepository: ProductRepository): ViewModel() {

    fun getRecommendedProduct() = viewModelScope.launch {
        val recommended = productRepository.getRangeProduct(1, 5)
        if (recommended.isSuccessful ) {
            println(recommended)
        }
    }

}



class MainViewModelFactory(private val productRepository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(productRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}