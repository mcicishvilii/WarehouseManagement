package com.example.warehousemanagement.ui.screens.brands

import androidx.lifecycle.ViewModel
import com.example.warehousemanagement.data.model.ItemsEntity
import com.example.warehousemanagement.data.repository.ItemsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val tasksRepo: ItemsRepositoryImpl,
) : ViewModel() {

    fun getTasks(): Flow<List<ItemsEntity>> {
        return tasksRepo.getItems()
    }

    fun getFilteredItems(query:String): Flow<List<ItemsEntity>> {
        return tasksRepo.getFilteredItems(query)
    }
}