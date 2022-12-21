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
import com.example.warehousemanagement.data.model.Cities
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

    val list = mutableListOf<Cities>()

    override fun viewCreated() {
        readFromDb()
//        readFromDBWithCustomObj()
    }

    override fun listeners() {
        goToAdd()
        saveToCsv()
        delete()
    }


    private fun saveToCsv() {
        db.collection("cities").document("KUT")
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    private fun goToAdd() {
        binding.addNutton.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToAddTaskFragment())
        }
    }


    fun readFromDBWithCustomObj() {
        setupRecycler()
        val db = db.collection("cities")
        db.get()
            .addOnSuccessListener { document ->
                val city = document.toObjects<Cities>()

                if (document != null) {
//                    Log.d(TAG, "DocumentSnapshot data: ${city?.regions}")
                    testAdapter.submitList(city)

                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

    fun readFromDb(){
        setupRecycler()
        val db = db.collection("cities")
        db.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val city = snapshot.toObjects<Cities>()
                testAdapter.submitList(city)
            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }

    private fun delete(){
        testAdapter.setOnItemClickListener{city,_ ->
            db.collection("cities").document(city.cityAbreviation)
                .delete()
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
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