package com.example.warehousemanagement.di

import android.content.Context
import androidx.room.Room
import com.example.warehousemanagement.data.db.DataBase
import com.example.warehousemanagement.data.db.ItemsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DBmodule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): DataBase {
        return Room.databaseBuilder(
            context,
            DataBase::class.java,"sample.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideItemsDao(db: DataBase): ItemsDao {
        return db.itemsDao
    }
}