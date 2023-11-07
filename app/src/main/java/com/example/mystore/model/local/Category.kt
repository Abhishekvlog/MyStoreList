package com.example.tummoc_project.model.local

data class Category(
    val id: Int,
    val name: String,
    val items: List<Item>
)

data class Item(
    val id: Int,
    val name: String,
    val icon: String,
    val price: Double
)