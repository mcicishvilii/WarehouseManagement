package com.example.warehousemanagement.data.db

import androidx.room.*
import com.example.warehousemanagement.data.model.ItemsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDao {
    @Query("SELECT * FROM items")
    fun getAll(): Flow<List<ItemsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: ItemsEntity)

    @Delete
    fun delete(user: ItemsEntity)

    @Update
    fun updateItem(task: ItemsEntity)

    @Query("DELETE FROM items")
    fun deleteAll()

    @Query("SELECT * FROM items WHERE brandName LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<ItemsEntity>>

}