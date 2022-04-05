package com.example.shopapp.repository.local.dao

import androidx.room.*
import com.example.shopapp.repository.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Transaction
    @Query("SELECT * FROM products")
    fun getProducts(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Query("DELETE FROM products WHERE products.prod_id = :id")
    suspend fun deleteProductById(id: Int)
}
