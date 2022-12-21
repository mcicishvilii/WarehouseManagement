package com.example.warehousemanagement.ui.screens.add

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.warehousemanagement.common.BaseFragment
import com.example.warehousemanagement.data.model.Items
import com.example.warehousemanagement.databinding.FragmentAddProductBinding
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.SetOptions
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
        testAdd()
    }


    private fun testAdd(){
        binding.addNutton.setOnClickListener {

            val cityName = binding.etClientName.text.toString()

            val city = Items.Cities(
                false,
                "Georgia",
                "Tbilisi",
                1000000,
                Items.Cities.Regions(listOf("Tbilisi","Kartli")),
                ""
            )
            db.collection("cities").document(cityName).set(city)
            Toast.makeText(requireContext(), "$cityName added to db", Toast.LENGTH_SHORT).show()
            findNavController().navigate(AddProductFragmentDirections.actionAddTaskFragmentToDashboardFragment())

        }
    }
}