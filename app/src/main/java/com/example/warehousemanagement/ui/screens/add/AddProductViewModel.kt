package com.example.warehousemanagement.ui.screens.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.warehousemanagement.data.model.ItemsEntity
import com.example.warehousemanagement.data.repository.ItemsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val itemsRepo: ItemsRepositoryImpl,
) : ViewModel () {

    fun insertTask(item: ItemsEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            itemsRepo.insertItem(item)
        }
    }

    val selectedDate: MutableLiveData<String> = MutableLiveData()

}