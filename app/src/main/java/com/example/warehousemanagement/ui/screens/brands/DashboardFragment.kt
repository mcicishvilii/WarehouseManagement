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
import com.example.warehousemanagement.data.model.ItemsFireStoreHolder
import com.example.warehousemanagement.databinding.FragmentDashboardBinding
import com.example.warehousemanagement.ui.adapters.BrandsAdapter
import com.example.warehousemanagement.ui.adapters.TestAdapter
import com.example.warehousemanagement.ui.screens.add.TAG
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
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

    val list = mutableListOf<Items>()
    override fun viewCreated() {
//        getItems()

    }

    override fun listeners() {
        goToAdd()
        saveToCsv()
    }


    private fun saveToCsv() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding.saveButton.setOnClickListener {
                    readFromDB()
//                    vm.toCsv()
                }
//            }
//        }
    }

    private fun goToAdd() {
        binding.addNutton.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToAddTaskFragment())
        }
    }


    fun readFromDB() {
        setupRecycler()

        val db = db.collection("cities").document(binding.etSearch.text.toString())

        db.get()
            .addOnSuccessListener { document ->
                val city = document.toObject<Items>()
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${city?.cities?.country}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
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