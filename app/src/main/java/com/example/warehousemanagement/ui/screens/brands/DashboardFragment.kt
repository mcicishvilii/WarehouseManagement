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
import com.example.warehousemanagement.databinding.FragmentDashboardBinding
import com.example.warehousemanagement.ui.adapters.BrandsAdapter
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

    private val brandsAdapter: BrandsAdapter by lazy { BrandsAdapter() }
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
//                vm.getTasks().collect() {
//                    brandsAdapter.submitList(it)
//                }

        val source = Source.CACHE
        db.collection("products").document("UIIII").get(source)
            .addOnSuccessListener { docs ->
                val brand = docs.toObject<Items>()
                Log.d(TAG,brand?.brandName.toString())
                binding.tvProductName.text = brand?.brandName.toString()
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents.", exception)
            }
    }

//    private fun delete(){
//        brandsAdapter.apply {
//            setOnItemClickListener{ brand,_ ->
//                vm.deleteItem(brand)
//            }
//        }
//    }

//    private fun setupRecycler() {
//        binding.rvBrands.apply {
//            adapter = brandsAdapter
//            layoutManager =
//                LinearLayoutManager(
//                    requireContext(),
//                    LinearLayoutManager.VERTICAL,
//                    false
//                )
//        }
//    }


}