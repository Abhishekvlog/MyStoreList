package com.example.mystore.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tummoc_project.model.local.CategoryDao
import com.example.tummoc_project.model.local.CategoryEntity
import com.example.tummoc_project.model.local.FavoriteItemEntity
import com.example.tummoc_project.model.local.ItemDao
import com.example.tummoc_project.model.local.ItemEntity

@Database(entities = [CategoryEntity::class, ItemEntity::class, FavoriteItemEntity::class], version = 2)
abstract class StoreDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun itemDao(): ItemDao
}