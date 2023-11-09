package com.example.mystore.viewModel

import androidx.lifecycle.ViewModel
import com.example.mystore.model.repositories.StoreRepository
import com.example.tummoc_project.model.local.FavoriteItemEntity
import kotlinx.coroutines.flow.Flow

class FavoriteViewModel(private val repository: StoreRepository) : ViewModel() {

    val favoriteItemsList : Flow<List<FavoriteItemEntity>> = repository.favoriteItems

}