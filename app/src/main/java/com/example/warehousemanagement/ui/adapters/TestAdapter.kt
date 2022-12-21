package com.example.warehousemanagement.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.warehousemanagement.data.model.Cities
import com.example.warehousemanagement.data.model.ItemsEntity
import com.example.warehousemanagement.databinding.SingleBrandLayoutBinding
import com.example.warehousemanagement.databinding.SingleItemLayoutBinding
import com.example.warehousemanagement.ui.screens.brands.DashboardFragmentDirections

class TestAdapter  :
    ListAdapter<Cities, TestAdapter.TestViewHolder>(
        ItemsCallback()
    ) {

    private lateinit var itemClickListener: (Cities, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TestViewHolder {
        val binding =
            SingleBrandLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TestViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bindData()
    }

    inner class TestViewHolder(private val binding: SingleBrandLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: Cities? = null

        fun bindData() {
            model = getItem(adapterPosition)
            binding.apply {
                binding.tvBrandName.text = model?.country
                binding.tvProductName.text = model?.name
                binding.tvBoxQuanntity.text = model?.population.toString()
            }
            binding.tvBrandName.setOnLongClickListener {
                itemClickListener.invoke(model!!, adapterPosition)
                true
            }

//            binding.tvItemCode.setOnClickListener {
//                val action = DashboardFragmentDirections.actionDashboardFragmentToItemsFragment()
//                binding.tvItemCode.findNavController().navigate(action)
//            }
        }
    }

    fun setOnItemClickListener(clickListener: (Cities, Int) -> Unit) {
        itemClickListener = clickListener
    }

}

class ItemsCallback :
    DiffUtil.ItemCallback<Cities>() {
    override fun areItemsTheSame(
        oldItem: Cities,
        newItem: Cities
    ): Boolean {
        return oldItem.country == newItem.country
    }

    override fun areContentsTheSame(
        oldItem: Cities,
        newItem: Cities
    ): Boolean {
        return oldItem == newItem
    }


}