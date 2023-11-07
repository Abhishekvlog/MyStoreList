package com.example.tummoc_project.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey
    val id: Int,
    val categoryId: Int,
    val name: String,
    val icon: String,
    val price: Double
)