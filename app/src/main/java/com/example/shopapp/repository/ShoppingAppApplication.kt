package com.example.shopapp.repository

import android.app.Application
import com.example.shopapp.repository.local.FavoriteLocalRepository
import com.example.shopapp.repository.local.ProductLocalRepository
import com.example.shopapp.repository.local.ProductRoomDatabase
import com.example.shopapp.repository.remote.RetrofitFactory
import com.example.shopapp.repository.remote.api.*
import com.example.shopapp.repository.remote.repository.*

class ShoppingAppApplication : Application(){

    private val database by lazy { ProductRoomDatabase.getDatabase(this) }
    val localRepository by lazy { ProductLocalRepository(database.productDao()) }
    val favoriteLocalRepository by lazy { FavoriteLocalRepository(database.favoriteDao()) }

    private val authApi by lazy { RetrofitFactory.getInstance<AuthApi>("http://10.0.2.2:8000/auth/")}
    val authRepository by lazy { AuthRepository(authApi) }

    private val registerApi by lazy { RetrofitFactory.getInstance<RegisterApi>("http://10.0.2.2:8000/register/")}
    val registerRepository by lazy { RegisterRepository(registerApi) }

    private val commentApi by lazy { RetrofitFactory.getInstance<CommentApi>("http://10.0.2.2:8000/comments/")}
    val commentRepository by lazy { CommentRepository(commentApi) }

    private val productApi by lazy { RetrofitFactory.getInstance<ProductApi>("http://10.0.2.2:8000/products/")}
    val productRepository by lazy { ProductRepository(productApi) }

}
