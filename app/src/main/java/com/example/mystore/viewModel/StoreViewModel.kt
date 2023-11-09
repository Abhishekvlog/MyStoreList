package com.example.mystore.viewModel

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mystore.model.repositories.StoreRepository
import com.example.tummoc_project.model.local.Category
import com.example.tummoc_project.model.local.CategoryEntity
import com.example.tummoc_project.model.local.FavoriteItemEntity
import com.example.tummoc_project.model.local.Item
import com.example.tummoc_project.model.local.ItemEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val repository: StoreRepository
    ) : ViewModel() {
    val categories: Flow<List<CategoryEntity>> = repository.category

    fun getItemsForCategory(categoryId: Int): Flow<List<ItemEntity>> {
        return repository.getItemForCategory(categoryId)
    }

    fun insertFavoriteItem(item : FavoriteItemEntity){
        repository.insertFavoriteItem(item)
    }
    fun getDataFromJsonFile(resources : Resources) : List<Category>{
        try {
            val assetsManager = resources.assets
                val jsonFile = "storeData.json"
                val inputStream =assetsManager.open(jsonFile)
                val jsonString = inputStream.bufferedReader().use { it.readText() }

        // Parse JSON into a list of categories
            val type = object : TypeToken<List<Category>>() {}.type
            val category = Gson().fromJson<List<Category>>(jsonString, type)
            return category
        }
        catch (e:Exception){
            Log.d("abhi", "getDataFromJsonFile: getting error in ${e.message}")
        }
        return  emptyList()
    }

   fun  getItemList(resources : Resources, itemType : String) : List<Item>{
        val category = getDataFromJsonFile(resources)
       val categoryItem = category.find { it.name == itemType }
       Log.d("abhi", "getItemList: all food item are ----------- \n${categoryItem?.items}")
       return categoryItem?.items ?: emptyList()
    }
}