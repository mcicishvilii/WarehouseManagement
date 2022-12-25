package com.example.warehousemanagement.ui.screens.add

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.warehousemanagement.R
import com.example.warehousemanagement.common.BaseFragment
import com.example.warehousemanagement.data.model.Items
import com.example.warehousemanagement.databinding.FragmentAddProductBinding
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

const val TAG = "mcicishvilii"



@AndroidEntryPoint
class AddProductFragment :
    BaseFragment<FragmentAddProductBinding>(FragmentAddProductBinding::inflate) {

    val c = Calendar.getInstance();
    val mYear = c.get(Calendar.YEAR);
    val mMonth = c.get(Calendar.MONTH);
    val mDay = c.get(Calendar.DAY_OF_MONTH);

    private val vm: AddProductViewModel by viewModels()
    val db = Firebase.firestore


    override fun viewCreated() {


        binding.etBrandImpl.setText("Pianca")
        binding.etBoxQuantityImpl.setText("12")
        binding.etClientNameImpl.setText("clienti")
        binding.etCoworkerSpinner.setText("ქეთი")
        binding.etStatusSpinner.setText("ასაღებია")
        binding.etLtdImpl.setText("სტუდიო")

        setupCoworkerSpinner()
        setupStatusSpinner()
        setUpLtdSpinner()
    }

    override fun listeners() {
        testAdd()
        showDatePickerDialog()
    }

    fun showDatePickerDialog() {
        binding.btnDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(requireContext(),
                OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    binding.etDateImpl.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                }, mYear, mMonth, mDay)
            datePickerDialog.show()
        }
    }



private fun testAdd() {
    binding.addNutton.setOnClickListener {


        val brand = binding.etBrandImpl.text.toString()
        val boxQuantity = binding.etBoxQuantityImpl.text.toString().toInt()
        val productName = binding.etProductNameImpl.text.toString()

        val coworker = binding.etCoworkerSpinner.text.toString()
        val status = binding.etStatusSpinner.text.toString()

        val clientName = binding.etClientNameImpl.text.toString()
        val timeStamp = binding.etDateImpl.text.toString()

        val ltd = binding.etLtdImpl.text.toString()


        val item = Items(
            ltd = ltd,
            brand = brand,
            productName = productName,
            clientName = clientName,
            coworker = coworker,
            status = status,
            boxNumber = boxQuantity,
            timestamp = timeStamp
        )

        try {
            if(checkIfEmpty()) {
//                db.collection("cities").document(timeStamp).set(item)

                db
                    .collection("products").document(timeStamp)
                    .collection(brand).document(productName).set(item)



                Toast.makeText(requireContext(), "$clientName added to db", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(AddProductFragmentDirections.actionAddTaskFragmentToDashboardFragment())
            }
        }
        catch (e:Exception){
            Toast.makeText(requireContext(), "შეავსეთ ცარიელი ველებიs", Toast.LENGTH_SHORT).show()
        }


    }
}

    private fun checkIfEmpty():Boolean{
        binding.apply {
            return if(
                etCoworkerSpinner.text.toString() == "ვისი შეკვეთაა" &&
                etStatusSpinner.text.toString() == "სტატუსი" &&
                etLtdImpl.text.toString() == "ფირმა"
            ){
                Toast.makeText(requireContext(), "please fill all items", Toast.LENGTH_SHORT).show()
                false
            } else{
                true
            }
        }
    }


    private fun setupCoworkerSpinner() {
        val coworker = resources.getStringArray(R.array.coworker)
        val adapter = ArrayAdapter(requireContext(), R.layout.custom_spinner_layout, coworker)
        binding.etCoworkerSpinner.setAdapter(adapter)
    }

    private fun setupStatusSpinner() {
        val status = resources.getStringArray(R.array.status)
        val adapter1 = ArrayAdapter(requireContext(), R.layout.custom_spinner_layout, status)
        binding.etStatusSpinner.setAdapter(adapter1)
    }

    private fun setUpLtdSpinner() {
        val ltd = resources.getStringArray(R.array.ltd)
        val adapter2 = ArrayAdapter(requireContext(), R.layout.custom_spinner_layout, ltd)
        binding.etLtdImpl.setAdapter(adapter2)
    }



}