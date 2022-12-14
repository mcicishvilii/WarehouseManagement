package com.example.warehousemanagement.data.repository

import android.util.Log
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

    override fun getFilteredItems(query:String): Flow<List<ItemsEntity>> {
        return itemsDao.searchDatabase(query)
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

    override suspend fun createCSV() {

        val sb = StringBuilder()
        var afterFirst = false
        sb.append("{POSTDATALOCAL}")
        for (s in itemsDao.getPostDataLocalCSV()) {
            if(afterFirst) sb.append(",")
            afterFirst = true
            sb.append(s)
        }
        afterFirst = false
        sb.append("{GROUPDATALOCAL}")

        Log.d("CSV_DATA","CSV is :-\n\t$sb")

    }

}