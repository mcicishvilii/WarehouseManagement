package com.example.warehousemanagement.ui.screens.add

import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.warehousemanagement.R
import com.example.warehousemanagement.common.BaseFragment
import com.example.warehousemanagement.common.DatePickerFragment
import com.example.warehousemanagement.data.model.Items
import com.example.warehousemanagement.databinding.FragmentAddProductBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

const val TAG = "mcicishvilii"

@AndroidEntryPoint
class AddProductFragment :
    BaseFragment<FragmentAddProductBinding>(FragmentAddProductBinding::inflate) {


    private val vm: AddProductViewModel by viewModels()
    val db = Firebase.firestore


    override fun viewCreated() {



        binding.etBrandImpl.setText("Pianca")
        binding.etBoxQuantityImpl.setText("12")
//        binding.etSkuImpl.setText("12")
        binding.etProductNameImpl.setText("Skami")
//        binding.etClientName.setText("misho")

        setupCoworkerSpinner()
    }

    override fun listeners() {
        testAdd()
        showDatePickerDialog()
    }


    fun showDatePickerDialog() {
        binding.btnDate.setOnClickListener {
            val newFragment = DatePickerFragment()
            newFragment.show(parentFragmentManager, "datePicker")
        }
    }


    private fun testAdd(){
        binding.addNutton.setOnClickListener {

            val brand = binding.etBrandImpl.text.toString()
            val boxQuantity = binding.etBoxQuantityImpl.text.toString().toInt()
            val sku = "misho"
            val productName = binding.etProductNameImpl.text.toString()

            val abbreviationBrand = binding.etBrandImpl.text.toString()
            val trimmedAbbreviation = abbreviationBrand.substring(0,3).uppercase()

            val clientName = binding.etClientNameImpl.text.toString()

            val calendar = Calendar.getInstance()
            val timeStamp = calendar.timeInMillis

            val item = Items(
                timestamp = timeStamp ,
                brandAbbreviation = trimmedAbbreviation,
                brand = brand,
                boxQuantity = boxQuantity,
                sku = sku,
                productName = productName,
                clientName = clientName

            )
            db.collection("cities").document(clientName).set(item)
            Toast.makeText(requireContext(), "$clientName added to db", Toast.LENGTH_SHORT).show()
            findNavController().navigate(AddProductFragmentDirections.actionAddTaskFragmentToDashboardFragment())

        }
    }


    private fun setupCoworkerSpinner() {
        val coworker = resources.getStringArray(R.array.coworker)
        val adapter = ArrayAdapter(requireContext(), R.layout.custom_spinner_layout,coworker)
        binding.etCoworkerSpinner.setAdapter(adapter)
    }

}