package com.example.warehousemanagement.ui.screens.add

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.warehousemanagement.common.BaseFragment
import com.example.warehousemanagement.data.model.Items
import com.example.warehousemanagement.databinding.FragmentAddProductBinding
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Field
import kotlin.collections.HashMap

const val TAG = "mcicishvilii"
@AndroidEntryPoint
class AddProductFragment :
    BaseFragment<FragmentAddProductBinding>(FragmentAddProductBinding::inflate) {


    private val vm: AddProductViewModel by viewModels()
    val db = Firebase.firestore



    override fun viewCreated() {


        binding.etBrand.setText("Pianca")
        binding.etBoxQuantity.setText("12")
        binding.etSku.setText("12")
        binding.etProductName.setText("Skami")
        binding.etClientName.setText("misho")
    }

    override fun listeners() {
//        addItem()
        addItemtoFirestore()
    }

    private fun addItemtoFirestore() {
        binding.addNutton.setOnClickListener {
            val product:MutableMap<String,Any> = HashMap()
            product["niko kecxoveli"] = listOf(
                binding.etClientName.text.toString(),
                binding.etBoxQuantity.text.toString().toInt(),
                binding.etSku.text.toString(),
                binding.etProductName.text.toString(),
                binding.etBrand.text.toString().uppercase())


            db.collection("products").document("UIIII")
                .update(binding.etBrand.text.toString().uppercase(),FieldValue.arrayUnion(product))
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "added", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(AddProductFragmentDirections.actionAddTaskFragmentToDashboardFragment())
                }
                .addOnFailureListener { e ->
                    Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                }
        }
    }
}