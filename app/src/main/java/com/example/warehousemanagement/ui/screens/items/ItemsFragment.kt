package com.example.warehousemanagement.ui.screens.items

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.warehousemanagement.R
import com.example.warehousemanagement.common.BaseFragment
import com.example.warehousemanagement.data.model.Items
import com.example.warehousemanagement.databinding.FragmentDashboardBinding
import com.example.warehousemanagement.databinding.FragmentItemsBinding
import com.example.warehousemanagement.ui.adapters.BrandsAdapter
import com.example.warehousemanagement.ui.adapters.ItemsAdapter
import com.example.warehousemanagement.ui.screens.add.TAG
import com.example.warehousemanagement.ui.screens.brands.DashboardViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ItemsFragment : BaseFragment<FragmentItemsBinding>(FragmentItemsBinding::inflate) {

    val args:ItemsFragmentArgs by navArgs()

    private val itemsAdapter: ItemsAdapter by lazy { ItemsAdapter() }
    private val vm: DashboardViewModel by viewModels()
    val db = Firebase.firestore

    override fun viewCreated() {
        readFromNestedDb()
    }

    override fun listeners() {

    }



    fun readFromNestedDb(){
        setupRecycler()
        val db = db.collection("products").document("26-12-2022").collection("Pianca")
        db.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val city = snapshot.toObjects<Items>()
                itemsAdapter.submitList(city)

            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }

//    private fun getItems() {
//        setupRecycler()
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                val query = args.itemInfo.brandName
//                vm.getFilteredItems(query!!).collect() {
////                    itemsAdapter.submitList(it)
//                }
//            }
//        }
//    }

    private fun setupRecycler() {
        binding.rvItems.apply {
            adapter = itemsAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }

}