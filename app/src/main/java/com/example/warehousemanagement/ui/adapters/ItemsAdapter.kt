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

class ItemsAdapter  :
    ListAdapter<String, ItemsAdapter.ItemsViewHolder>(
        NewsDiffCallBack()
    ) {

//    private lateinit var itemClickListener: (Items, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ItemsViewHolder {
        val binding =
            SingleItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bindData()
    }

    inner class ItemsViewHolder(private val binding: SingleItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: String? = null

        fun bindData() {
            model = getItem(adapterPosition)
            binding.apply {
                binding.tvItemName.text = model
//                binding.tvBrand.text = model?.brand
//                binding.tvBoxQuentity.text = model?.boxNumber.toString()
//                binding.tvOrderNumber.text = model?.clientName
//
//                binding.tvTimestamp.text = model?.timestamp
//                binding.tvCoworker.text = model?.coworker
//                binding.tvProductName.text = model?.productName
//                binding.tvLtd.text = model?.ltd
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
//
//    fun setOnItemClickListener(clickListener: (ItemsEntity, Int) -> Unit) {
//        itemClickListener = clickListener
//    }

}

class NewsDiffCallBack :
    DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(
        oldItem: String,
        newItem: String
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: String,
        newItem: String
    ): Boolean {
        return oldItem == newItem
    }


}