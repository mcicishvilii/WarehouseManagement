package com.example.warehousemanagement.data.repository

import com.example.warehousemanagement.data.db.ItemsDao
import com.example.warehousemanagement.data.model.ItemsEntity
import com.example.warehousemanagement.domain.repository.ItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val itemsDao: ItemsDao
): ItemsRepository{
    override fun getItems(): Flow<List<ItemsEntity>> {
        return itemsDao.getAll()
    }

    override suspend fun insertItem(item: ItemsEntity) {
        return itemsDao.insert(item)
    }

    override suspend fun deleteItem(item: ItemsEntity) {

        return itemsDao.delete(item)
    }

    override suspend fun deleteAll() {

        return itemsDao.deleteAll()
    }

    override suspend fun updateItem(item: ItemsEntity) {
        return itemsDao.updateItem(item)
    }

}