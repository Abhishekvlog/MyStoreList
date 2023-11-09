package com.example.mystore.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.mystore.model.local.StoreDatabase
import com.example.mystore.model.repositories.StoreRepository
import com.example.mystore.viewModel.FavoriteViewModel
import com.example.mystore.viewModel.StoreViewModel
import com.example.tummoc_project.model.local.CategoryDao
import com.example.tummoc_project.model.local.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoreModule {

    @Provides
    @Singleton
    fun provideMyDatabase(@ApplicationContext context: Context) : StoreDatabase{
        return  Room.databaseBuilder(context, StoreDatabase::class.java, "store_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(database : StoreDatabase) : CategoryDao{
        return database.categoryDao()
    }

    @Provides
    @Singleton
    fun provideItemDao(database: StoreDatabase) : ItemDao{
        return database.itemDao()
    }

    @Provides
    @Singleton
    fun provideMyRepository(
        categoryDao: CategoryDao,
        itemDao: ItemDao
    ) : StoreRepository{
        return StoreRepository(categoryDao,itemDao)
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(application: Application) : ViewModelProvider.Factory{
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    @Provides
    @Singleton
    fun provideViewModel(
        repository: StoreRepository
    ): StoreViewModel{
        return  StoreViewModel(repository)
    }

    @Provides
    @Singleton
    fun provideFavoriteItemViewModel(
        repository: StoreRepository
    ) : FavoriteViewModel{
        return FavoriteViewModel(repository)
    }
}