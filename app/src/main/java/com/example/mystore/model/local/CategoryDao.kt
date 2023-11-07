package com.example.tummoc_project.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: List<CategoryEntity>)

    @Query("select * from categories")
    fun getCategory() : Flow<List<CategoryEntity>>

}

@Dao
interface ItemDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: List<ItemEntity>)

    @Query("SELECT * FROM items WHERE categoryId = :categoryId")
    fun getItemForCategory(categoryId : Int) : Flow<List<ItemEntity>>
}