package com.example.warehousemanagement.ui.screens.add

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.warehousemanagement.R
import com.example.warehousemanagement.common.BaseFragment
import com.example.warehousemanagement.data.model.ItemsEntity
import com.example.warehousemanagement.databinding.FragmentAddProductBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

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

    private fun addItem() {
        binding.addNutton.setOnClickListener {
            val task = ItemsEntity(
                0,
                binding.etClientName.text.toString(),
                binding.etBoxQuantity.text.toString().toInt(),
                binding.etSku.text.toString(),
                binding.etProductName.text.toString(),
                binding.etBrand.text.toString().uppercase()
            )
            vm.insertTask(task)
            findNavController().navigate(AddProductFragmentDirections.actionAddTaskFragmentToDashboardFragment())
        }
    }

    private fun addItemtoFirestore() {
        binding.addNutton.setOnClickListener {
            val product = hashMapOf(
                "clientName" to binding.etClientName.text.toString(),
                "boxQuantiry" to binding.etBoxQuantity.text.toString().toInt(),
                "sku" to binding.etSku.text.toString(),
                "productName" to binding.etProductName.text.toString(),
                "brand" to binding.etBrand.text.toString().uppercase()
            )

            db.collection("products")
                .add(product)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    findNavController().navigate(AddProductFragmentDirections.actionAddTaskFragmentToDashboardFragment())
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
    }
}