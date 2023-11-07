package com.example.mystore.viewModel

import android.app.Application
import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.model.repositories.MyRepository
import com.example.tummoc_project.model.local.CategoryEntity
import com.example.tummoc_project.model.local.ItemEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MyRepository
    ) : ViewModel() {
    val categories: Flow<List<CategoryEntity>> = repository.category

    fun getItemsForCategory(categoryId: Int): Flow<List<ItemEntity>> {
        return repository.getItemForCategory(categoryId)
    }

    fun initializeDataFromJson(resources : Resources){
        viewModelScope.launch {
            try {
                val assetsManager = resources.assets
                val jsonFile = "storeData.json"
                val inputStream =assetsManager.open(jsonFile)
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                repository.initializeDataFromJson(jsonString)
                Log.d("abhi", "JSON data is going to initialize: $jsonString")
            }
            catch (e: Exception){
                Log.e("abhi", "Error loading JSON data from assets", e)

            }
        }
    }


}