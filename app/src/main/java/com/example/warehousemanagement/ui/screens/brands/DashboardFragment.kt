package com.example.warehousemanagement.ui.screens.brands

import android.os.Environment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.warehousemanagement.common.BaseFragment
import com.example.warehousemanagement.data.db.ItemsDao
import com.example.warehousemanagement.databinding.FragmentDashboardBinding
import com.example.warehousemanagement.ui.adapters.BrandsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileWriter
import javax.inject.Inject


@AndroidEntryPoint
class DashboardFragment @Inject constructor(
    private val itemsDao: ItemsDao,
)  : BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    private val brandsAdapter: BrandsAdapter by lazy { BrandsAdapter() }
    private val vm: DashboardViewModel by viewModels()

    override fun viewCreated() {
        getItems()
    }

    override fun listeners() {
        goToAdd()
        delete()
        saveToCsv()
    }


    private fun saveToCsv() {
        

    }

    private fun goToAdd(){
        binding.addNutton.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToAddTaskFragment())
        }
    }

    private fun getItems() {
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.getTasks().collect() {
                    brandsAdapter.submitList(it)
                }
            }
        }
    }

    private fun delete(){
        brandsAdapter.apply {
            setOnItemClickListener{ brand,_ ->
                vm.deleteItem(brand)
            }
        }
    }

    private fun setupRecycler() {
        binding.rvBrands.apply {
            adapter = brandsAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }

}