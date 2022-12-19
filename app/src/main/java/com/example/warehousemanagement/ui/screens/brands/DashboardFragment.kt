package com.example.warehousemanagement.ui.screens.brands

import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.warehousemanagement.common.BaseFragment
import com.example.warehousemanagement.data.db.ItemsDao
import com.example.warehousemanagement.data.model.Items
import com.example.warehousemanagement.data.model.itemsList
import com.example.warehousemanagement.databinding.FragmentDashboardBinding
import com.example.warehousemanagement.ui.adapters.BrandsAdapter
import com.example.warehousemanagement.ui.adapters.TestAdapter
import com.example.warehousemanagement.ui.screens.add.TAG
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileWriter
import javax.inject.Inject


@AndroidEntryPoint
class DashboardFragment :
    BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    private val testAdapter: TestAdapter by lazy { TestAdapter() }
    private val vm: DashboardViewModel by viewModels()
    val db = Firebase.firestore

    override fun viewCreated() {
        getItems()
    }

    override fun listeners() {
        goToAdd()
        saveToCsv()
    }


    private fun saveToCsv() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding.saveButton.setOnClickListener {
                    vm.toCsv()
                }
            }
        }
    }

    private fun goToAdd() {
        binding.addNutton.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToAddTaskFragment())
        }
    }

    private fun getItems() {
        setupRecycler()
        Log.d(TAG, itemsList.toString())
//                vm.getTasks().collect() {
//                    brandsAdapter.submitList(it)
//                }
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                val database = db.collection("products")
                database.addSnapshotListener { snapshots, e ->
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e)
                        return@addSnapshotListener
                    }
                    if (snapshots != null) {
                        for (snapshot in snapshots)
                            if (snapshot != null && snapshot.exists()) {
                                testAdapter.submitList(itemsList.toList())
                                Log.d(TAG, "Current data: ${snapshot.data}")
                            } else {
                                Log.d(TAG, "Current data: null")
                            }
                    }
                }
            }
        }

    }

//    private fun delete(){
//        brandsAdapter.apply {
//            setOnItemClickListener{ brand,_ ->
//                vm.deleteItem(brand)
//            }
//        }
//    }

    private fun setupRecycler() {
        binding.rvBrands.apply {
            adapter = testAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }


}