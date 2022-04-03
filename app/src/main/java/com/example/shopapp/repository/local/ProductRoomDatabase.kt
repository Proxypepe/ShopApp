package com.example.shopapp.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shopapp.repository.local.dao.ProductDao
import com.example.shopapp.repository.local.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductRoomDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {

        @Volatile
        private var INSTANCE: ProductRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): ProductRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductRoomDatabase::class.java,
                    "products"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}