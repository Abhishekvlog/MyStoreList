package com.example.mystore.model.repositories

import com.example.tummoc_project.model.local.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MyRepository @Inject constructor(
    private  val categoryDao: CategoryDao,
    private val itemDao: ItemDao
) {

    val category : Flow<List<CategoryEntity>> = categoryDao.getCategory()

    fun getItemForCategory(categoryId : Int) : Flow<List<ItemEntity>> {
        return  itemDao.getItemForCategory(categoryId)
    }

    suspend fun initializeDataFromJson(jsonString : String){

        val category : List<Category> = Gson().fromJson(jsonString, object : TypeToken<List<Category>>() {}.type)
        val categoryEntity = category.map { CategoryEntity(it.id, it.name) }

        val itemEntity = category.flatMap { category ->
            category.items.map {
                ItemEntity(it.id, category.id, it.name, it.icon, it.price)
            }
        }
        print("data of category entity class  ---------${categoryEntity}")
        categoryDao.insertCategory(categoryEntity)
        print("data of item entity class  ---------${itemEntity}")
        itemDao.insertItem(itemEntity)
    }
}