package com.example.warehousemanagement.ui.screens.items

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
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
import com.example.warehousemanagement.databinding.FragmentDashboardBinding
import com.example.warehousemanagement.databinding.FragmentItemsBinding
import com.example.warehousemanagement.ui.adapters.BrandsAdapter
import com.example.warehousemanagement.ui.adapters.ItemsAdapter
import com.example.warehousemanagement.ui.screens.brands.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ItemsFragment : BaseFragment<FragmentItemsBinding>(FragmentItemsBinding::inflate) {

    val args:ItemsFragmentArgs by navArgs()

    private val itemsAdapter: ItemsAdapter by lazy { ItemsAdapter() }
    private val vm: DashboardViewModel by viewModels()

    override fun viewCreated() {
        getItems()
    }

    override fun listeners() {

    }

    private fun getItems() {
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val query = args.itemInfo.brandName
                vm.getFilteredItems(query!!).collect() {
                    itemsAdapter.submitList(it)
                }
            }
        }
    }

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