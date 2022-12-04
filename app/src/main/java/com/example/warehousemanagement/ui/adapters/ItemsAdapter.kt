package com.example.warehousemanagement.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.warehousemanagement.data.model.ItemsEntity
import com.example.warehousemanagement.databinding.SingleItemLayoutBinding
import com.example.warehousemanagement.ui.screens.brands.DashboardFragmentDirections

class ItemsAdapter  :
    ListAdapter<ItemsEntity, ItemsAdapter.ItemsViewHolder>(
        NewsDiffCallBack()
    ) {

    private lateinit var itemClickListener: (ItemsEntity, Int) -> Unit

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
        private var model: ItemsEntity? = null

        fun bindData() {
            model = getItem(adapterPosition)
            binding.apply {
                binding.tvItemCode.text = model?.Name
            }
            binding.tvItemCode.setOnLongClickListener {
                itemClickListener.invoke(model!!, adapterPosition)
                true
            }

            binding.tvItemCode.setOnClickListener {
                val action = DashboardFragmentDirections.actionDashboardFragmentToItemsFragment()
                binding.tvItemCode.findNavController().navigate(action)
            }
        }
    }

    fun setOnItemClickListener(clickListener: (ItemsEntity, Int) -> Unit) {
        itemClickListener = clickListener
    }

}

class NewsDiffCallBack :
    DiffUtil.ItemCallback<ItemsEntity>() {
    override fun areItemsTheSame(
        oldItem: ItemsEntity,
        newItem: ItemsEntity
    ): Boolean {
        return oldItem.ArtistId== newItem.ArtistId
    }

    override fun areContentsTheSame(
        oldItem: ItemsEntity,
        newItem: ItemsEntity
    ): Boolean {
        return oldItem == newItem
    }


}