package com.example.warehousemanagement.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.warehousemanagement.data.model.ItemsEntity

@Database(entities = [ItemsEntity::class], version = 4)

abstract class DataBase: RoomDatabase() {
    abstract val itemsDao: ItemsDao

}