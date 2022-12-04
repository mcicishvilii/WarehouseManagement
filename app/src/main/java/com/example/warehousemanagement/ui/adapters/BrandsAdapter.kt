package com.example.warehousemanagement.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.warehousemanagement.data.model.ItemsEntity
import com.example.warehousemanagement.databinding.SingleBrandLayoutBinding
import com.example.warehousemanagement.databinding.SingleItemLayoutBinding
import com.example.warehousemanagement.ui.screens.brands.DashboardFragmentDirections

class BrandsAdapter  :
    ListAdapter<ItemsEntity, BrandsAdapter.BrandsViewHolder>(
        BrandsDiffCallback()
    ) {

    private lateinit var itemClickListener: (ItemsEntity, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): BrandsViewHolder {
        val binding =
            SingleBrandLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: BrandsViewHolder, position: Int) {
        holder.bindData()
    }

    inner class BrandsViewHolder(private val binding: SingleBrandLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: ItemsEntity? = null

        fun bindData() {
            model = getItem(adapterPosition)
            binding.apply {
                binding.tvBrandName.text = model?.brandName
                binding.tvBoxQuanntity.text = model?.boxNumber.toString()
            }
            binding.tvBrandName.setOnLongClickListener {
                itemClickListener.invoke(model!!, adapterPosition)
                true
            }

            binding.tvBrandName.setOnClickListener {
                val action = DashboardFragmentDirections.actionDashboardFragmentToItemsFragment(model!!)
                binding.tvBrandName.findNavController().navigate(action)
            }
        }
    }

    fun setOnItemClickListener(clickListener: (ItemsEntity, Int) -> Unit) {
        itemClickListener = clickListener
    }

}

class BrandsDiffCallback :
    DiffUtil.ItemCallback<ItemsEntity>() {
    override fun areItemsTheSame(
        oldItem: ItemsEntity,
        newItem: ItemsEntity
    ): Boolean {
        return oldItem.itemId== newItem.itemId
    }

    override fun areContentsTheSame(
        oldItem: ItemsEntity,
        newItem: ItemsEntity
    ): Boolean {
        return oldItem == newItem
    }


}