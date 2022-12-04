package com.example.warehousemanagement.domain.repository

import com.example.warehousemanagement.data.model.ItemsEntity
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
    fun getItems(): Flow<List<ItemsEntity>>

    fun getFilteredItems(query:String): Flow<List<ItemsEntity>>

    suspend fun insertItem(item: ItemsEntity)

    suspend fun deleteItem(item: ItemsEntity)

    suspend fun deleteAll()

    suspend fun updateItem(item:ItemsEntity)
}