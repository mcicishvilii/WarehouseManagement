package com.example.warehousemanagement.ui.screens.brands

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.warehousemanagement.common.BaseFragment
import com.example.warehousemanagement.data.model.Date
import com.example.warehousemanagement.data.model.Items
import com.example.warehousemanagement.databinding.FragmentDashboardBinding
import com.example.warehousemanagement.ui.adapters.TestAdapter
import com.example.warehousemanagement.ui.screens.add.TAG
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardFragment :
    BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    private val testAdapter: TestAdapter by lazy { TestAdapter() }
    private val vm: DashboardViewModel by viewModels()
    val db = Firebase.firestore


    override fun viewCreated() {
        readFromDb()
    }

    override fun listeners() {

//        delete()
        goToAdd()
        moveToItem()
    }


    private fun moveToItem(){
        testAdapter.setOnItemClickListener { items, i ->
//            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToItemsFragment())
        }
    }


    private fun goToAdd() {
        binding.addNutton.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToAddTaskFragment())
        }
    }

    fun readFromDb(){
        setupGridRecycler()
        val db = db.collection("products")
        db.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val city = snapshot.toObjects<Date>()
                Log.d(TAG,city.toString())
                testAdapter.submitList(city)

            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }



//
//    private fun delete(){
//        testAdapter.setOnItemClickListener{item,_ ->
//            db.collection("cities").document(item.timestamp)
//                .delete()
//                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
//                .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
//        }
//    }



    private fun setupGridRecycler() {
        binding.rvBrands.apply {
            adapter = testAdapter
            layoutManager =
                GridLayoutManager(
                    requireContext(),
                    2,
                    GridLayoutManager.VERTICAL,
                    false
                )
        }
    }



}