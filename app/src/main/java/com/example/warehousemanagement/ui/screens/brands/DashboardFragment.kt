package com.example.warehousemanagement.ui.screens.brands

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.warehousemanagement.common.BaseFragment
import com.example.warehousemanagement.data.model.Items
import com.example.warehousemanagement.databinding.FragmentDashboardBinding
import com.example.warehousemanagement.ui.adapters.TestAdapter
import com.example.warehousemanagement.ui.screens.add.TAG
import com.google.firebase.firestore.ktx.firestore
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

    fun readFromDb(){
        setupRecycler()
        val db = db.collection("cities")
        db.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val city = snapshot.toObjects<Items>()
                testAdapter.submitList(city)
            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }

    private fun delete(){
        testAdapter.setOnItemClickListener{item,_ ->
            db.collection("cities").document(item.brandAbbreviation)
                .delete()
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
        }
    }


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