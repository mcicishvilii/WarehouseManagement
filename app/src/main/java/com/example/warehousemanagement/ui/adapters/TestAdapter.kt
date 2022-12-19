package com.example.warehousemanagement.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.warehousemanagement.data.model.Items
import com.example.warehousemanagement.data.model.ItemsEntity
import com.example.warehousemanagement.databinding.SingleItemLayoutBinding
import com.example.warehousemanagement.ui.screens.brands.DashboardFragmentDirections

class TestAdapter  :
    ListAdapter<Items, TestAdapter.TestViewHolder>(
        ItemsCallback()
    ) {

//    private lateinit var itemClickListener: (ItemsEntity, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TestViewHolder {
        val binding =
            SingleItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TestViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bindData()
    }

    inner class TestViewHolder(private val binding: SingleItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: Items? = null

        fun bindData() {
            model = getItem(adapterPosition)
            binding.apply {
                binding.tvItemName.text = model?.itemName
                binding.tvBrand.text = model?.brandName
                binding.tvBoxQuentity.text = model?.boxNumber.toString()
                binding.tvOrderNumber.text = model?.client
            }
//            binding.tvItemName.setOnLongClickListener {
//                itemClickListener.invoke(model!!, adapterPosition)
//                true
//            }

//            binding.tvItemCode.setOnClickListener {
//                val action = DashboardFragmentDirections.actionDashboardFragmentToItemsFragment()
//                binding.tvItemCode.findNavController().navigate(action)
//            }
        }
    }

//    fun setOnItemClickListener(clickListener: (ItemsEntity, Int) -> Unit) {
//        itemClickListener = clickListener
//    }

}

class ItemsCallback :
    DiffUtil.ItemCallback<Items>() {
    override fun areItemsTheSame(
        oldItem: Items,
        newItem: Items
    ): Boolean {
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(
        oldItem: Items,
        newItem: Items
    ): Boolean {
        return oldItem == newItem
    }


}