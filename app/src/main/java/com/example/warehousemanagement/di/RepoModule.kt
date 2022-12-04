package com.example.warehousemanagement.di

import com.example.warehousemanagement.data.repository.ItemsRepositoryImpl
import com.example.warehousemanagement.domain.repository.ItemsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    @Singleton
    abstract fun provideItemsRepo(repoImpl: ItemsRepositoryImpl): ItemsRepository
}