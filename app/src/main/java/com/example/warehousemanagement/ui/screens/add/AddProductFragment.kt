package com.example.warehousemanagement.ui.screens.add

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.warehousemanagement.R
import com.example.warehousemanagement.common.BaseFragment
import com.example.warehousemanagement.data.model.Items
import com.example.warehousemanagement.data.model.ItemsEntity
import com.example.warehousemanagement.databinding.FragmentAddProductBinding
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

const val TAG = "mcicishvilii"
@AndroidEntryPoint
class AddProductFragment :
    BaseFragment<FragmentAddProductBinding>(FragmentAddProductBinding::inflate) {


    private val vm: AddProductViewModel by viewModels()
    val db = Firebase.firestore


    override fun viewCreated() {

    }

    override fun listeners() {
//        addItem()
        addItemtoFirestore()
    }

    private fun addItemtoFirestore() {
        binding.addNutton.setOnClickListener {
            val product = Items(
//                serverTimestamp(),
                binding.etClientName.text.toString(),
                binding.etBoxQuantity.text.toString().toInt(),
                binding.etSku.text.toString(),
                binding.etProductName.text.toString(),
                binding.etBrand.text.toString().uppercase()
            )

            db.collection("products").document(binding.etBrand.text.toString().uppercase())
                .set(product)
                .addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot added")
                    findNavController().navigate(AddProductFragmentDirections.actionAddTaskFragmentToDashboardFragment())
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
    }
}