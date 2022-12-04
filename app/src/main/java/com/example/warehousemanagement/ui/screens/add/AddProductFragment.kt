package com.example.warehousemanagement.ui.screens.add

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.warehousemanagement.R
import com.example.warehousemanagement.common.BaseFragment
import com.example.warehousemanagement.data.model.ItemsEntity
import com.example.warehousemanagement.databinding.FragmentAddProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductFragment : BaseFragment<FragmentAddProductBinding>(FragmentAddProductBinding::inflate){

    private val vm: AddProductViewModel by viewModels()

    override fun viewCreated() {

    }

    override fun listeners() {
        addItem()
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
}