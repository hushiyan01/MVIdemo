package com.myworkshop.myapplication.model

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class LocalRepository @Inject constructor(private val itemDao: ItemDao) {
    fun getAllItems():Flow<List<Item>>{
        return itemDao.getAllItem()
    }

    suspend fun insertItem(item: Item){
        itemDao.addItem(item = item)
    }

    suspend fun updateItem(item: Item){
        itemDao.updateItem(item = item)
    }
}